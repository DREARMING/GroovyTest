import groovy.json.*

class Person{
	String name
	int age
	Date birthday
	String address
	URL url
}

// ------------- JsonOutput   ---------

//打印 对象Json
def person = new Person(name:"user1", age:12, birthday:new Date(), url: new URL("http://www.baidu.com"))
def personJson = JsonOutput.toJson(person)
println personJson

//打印 map json
def mapJson = JsonOutput.toJson([name:"user2", age:13])
println mapJson

//完美打印,需要基于 json正常的字符串
def personPrintJson = JsonOutput.prettyPrint(personJson)
println personPrintJson

//设置 json 转换format
def generator = new JsonGenerator.Options()
    .excludeNulls()
    .dateFormat('yyyyMMdd-HHmm')	//设置日期打印格式
    .excludeFieldsByName('age')		//不打印字段，多个字段用 ，分开
    .excludeFieldsByType(URL)		//不打印该类型的字段
    .build()

println generator.toJson(person)


// ------------- JsonParse   ---------
def slurper = new JsonSlurper()

def person1 = slurper.parseText(personJson)

assert person1 instanceof Map
println person1

def task(Map<String,?> map, String name, Closure closure){
	closure("$name, $map")
}

def printClosure = { println it }

task( [type:"jar"],'hello', printClosure)