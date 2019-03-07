println 'hello world ! groovy'

//声明单引号字符串，无法拼接。 如果单引号字符串只有一个字符，将会默认转成char类型
def str1 = ' \' str1 \''
println str1

//声明双引号字符串，可以拼接
def str2 = "str2"
println str2

//双引号字符串拼接实例
def str3 = "str3 : ${str2}"
println str3

// 拼接符号 ${} ，里面可以做基本运算
def str4 = "str4 : ${str2 + '4'}"
println str4

//声明 list，数组元素是可以任意类型
def list = ['str', 1, true]
//断言
assert 'str' == list[0]

assert list[2] == true
println "list size is : ${list.size()}"

//向数组末尾插入新元素
list << 'insert item'
println "list size is : ${list.size()} , last item is ${list[-1]}"

//遍历
for(def item : list){
	println item
}

list.each{
	println it
}

//如果数组元素类型一致，可以强转型
def list2 = [1,2,3] as int[]

assert (list2 instanceof int[])

//数组
String[] strArray = ['item1','item2']
println strArray
println "strArray size is : ${strArray.size()}"

//strArray[2] = 'item3' 无法执行
// strArray << ['item3'] 无法执行
//println strArray

//定义键值 map
def map = ['key1':"value1", 'key2':2, 'key3' : true]

println map.size()

//可以直接向数组那样，key当作下表引用map的值
println map['key1']

//可以用 . 操作符，直接打印 值
println map.key2

//遍历
map.each{
	println "${it.key} : ${it.value}"
}

assert map instanceof java.util.LinkedHashMap



