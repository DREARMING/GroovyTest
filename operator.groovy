
//类操作符

class Person{
	public final String name
	private final int age = 2
	Person(String name){
		this.name = name
	}
	String getName(){
		return "Name : ${name}"
	}
}

def person = new Person('bob')
// 调用类的name属性， 会执行类的 getName 方法，这是groovy的语法规则
assert  person.name == 'Name : bob'

//可以用@直接引用属性值
assert person.@name == 'bob'

//可以访问私有属性
assert person.@age == 2

// .& 操作符 用于将被调用的方法存入一个方法变量中，接着只要调用这个变量就相当于调用之前的那个方法
def str = 'example of method reference'
def fun = str.&toUpperCase           
def upper = fun()        
assert upper == str.toUpperCase()

// Range Operator 范围操作符
def range = 1..10
assert range instanceof java.util.List
assert range.size() == 10
assert range[2] == 3

// <=> 比较操作符，内部用了 compareTo 方法(相等为0 、 大于为1，小于为-1)
assert 1<=>1 == 0
println "1 <=> 1 ==  ${1 <=> 1}"

// in 操作符
def list = ['item1', 'item3']
assert ('item1' in list)  // => list.contains('item1')

// groovy 的 is 操作符相当于 java的引用比较， 即 java 的== 操作符
// groovy 的 == 操作符相当于调用java 的equal方法
def temp1 = [1,2]
def temp2 = [1,2]
assert temp1 == temp2
assert !temp1.is(temp2)

// as 类型转换操作符
Integer int1 = 2
String str1 = int1 as String
println str1

class Identifiable {
    String name
}
class User {
    Long id
    String name
    //类型转换函数
    def asType(Class target) {                                              
        if (target==Identifiable) {
            return new Identifiable(name: name)
        }
        throw new ClassCastException("User cannot be coerced into $target")
    }
}
def u = new User(name: 'Xavier')                                            
def p = u as Identifiable                                                   
assert p instanceof Identifiable                                            
assert !(p instanceof User) 


//操作符重载
class Bucket {
    int size

    Bucket(int size) { this.size = size }

    Bucket plus(Bucket other) {                     
        return new Bucket(this.size + other.size)
    }
}

def b1 = new Bucket(4)
def b2 = new Bucket(11)
assert (b1 + b2).size == 15 
