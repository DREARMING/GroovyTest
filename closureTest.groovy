
//声明一个无参闭包
def closure1 = { println "i am a  closure!!" }

//声明一个有参闭包
def closure2 = { x, y -> println "$x + $y == ${x+y}"}

//闭包没有声明参数，会隐含一个默认参数，it
def closure3 = {println it}

//闭包的调用
closure1()

closure1.call()

closure2(1,9)

closure3("hello closure3")


//方法的声明:

//无返回值无参函数
def method1(){
	println 'method1'
}

//可以不声明参数类型
def method2(arg1){
	println arg1
}


method2('call method2 ')
method2 '没有括号的方法调用，方法调用不需要加括号，参数之间用逗号分隔开'


def method3(arg1, closure){
	closure(arg1)
}

// 闭包作为最后一个参数， 可以不放进括号内
method3('method3'){
	println it
}

def method4(){
	def returnValue = 10
	//方法的最后一行代表返回值
	returnValue
}

println "method 4 de return value : ${method4()}"


//方法 命名参数
def method5(Map map){
	println map.type
}

method5(type:'PersonType')


// 类方法声明
class User {
	//没有声明 作用域，默认为 public，并且提供默认的 get、set方法
	String name
	int age;

	//无参构造
	User(){}

	//有参构造,
	User(name, age){
		this.name = name
		this.age = age
	}

	def getStudentNum(){
		println "student num is : 100$name - age:$age"
	}
}

def user = new User()
user.name = '86'
user.getStudentNum()


def user2 = new User('86', 20)
user2.getStudentNum()

//因为构造参数的位置是固定的，可以直接用map来构造

def user3 = ['86',30] as User
user3.getStudentNum()

//当一个类没有声明构造参数时，默认提供一个 命名构造参数,可以用键值对进行 field 赋值
class Person{
	String name
	int number

	String toString(){
		"name :$name , number:$number"
	}
}

def person = new Person(number:10010)
println person.number

def person2 = new Person(name:'10010')
println person2



//闭包的委托
//背景知识：
// 闭包是 Closure 类的对象，其中有3个成员比较重要，分别是 this、ower、delegate
// this 代表 闭包的外围类对象（不包含闭包），ower 代表闭包的直接外围类（包含闭包、类），delegate 默认跟 ower一样，但是可以指定为其他对象
// 闭包有执行策略：
// 默认是 ower_first, 即如果闭包 引用到的变量，先用ower的，找不到再用 delegate 的
// delegate_first, 跟上述相反。 ower_only,只查找ower里面的变量。 deledate_oly, 只查找delegate 里面的

class Enclosing {
    void run() {
        def whatIsThisObject = { getThisObject() }          
        assert whatIsThisObject() == this                   
        def whatIsThis = { this }                           
        assert whatIsThis() == this                         
    }
}
class EnclosedInInnerClass {
    class Inner {
        Closure cl = { this }                               
    }
    void run() {
        def inner = new Inner()
        assert inner.cl() == inner                          
    }
}
class NestedClosures {
    void run() {
        def nestedClosures = {
            def cl = { this }                               
            cl()
        }
        assert nestedClosures() == this                     
    }
}


//跟 this 唯一不同的是 NestedClosuresOwer 中，ower指的是其直接外围闭包，而不是外围类
class EnclosingOwner {
    void run() {
        def whatIsOwnerMethod = { getOwner() }               
        assert whatIsOwnerMethod() == this                   
        def whatIsOwner = { owner }                          
        assert whatIsOwner() == this                         
    }
}
class EnclosedInInnerClassOwner {
    class Inner {
        Closure cl = { owner }                               
    }
    void run() {
        def inner = new Inner()
        assert inner.cl() == inner                           
    }
}

class NestedClosuresOwner {
    void run() {
        def nestedClosures = {
            def cl = { owner }                               
            cl()
        }
        assert nestedClosures() == nestedClosures            
    }
}


name = "delegate name"

//
def delegateClosure = {
	println name
	name
}

assert name == delegateClosure()

//可以使用 闭包delegate 
delegateClosure.delegate = person2
assert name == delegateClosure()

delegateClosure.delegate = user
assert name == delegateClosure()



//如果我们设置 delegate_first, 就不会直接用 name 属性了
//
delegateClosure.resolveStrategy = Closure.DELEGATE_FIRST

delegateClosure.delegate = person2
delegateClosure()

delegateClosure.delegate = user
delegateClosure()
//assert !(name == delegateClosure())
