
def srcFile = new File('1024.png')
assert srcFile.exists()

// 文件复制
def targetFile = new File('1025.png')

targetFile.withOutputStream { out ->
	srcFile.withInputStream { inputStream ->
		out<<inputStream
	}
}


// xml 读取
def manifestFile = new File('AndroidManifest.xml')
assert manifestFile.exists()

def parserX = new XmlSlurper()
def gpath = parserX.parse(manifestFile)

println "package: ${gpath.@package}"

println "application : ${gpath.application['@android:name']}"

def activitys = gpath.application.activity

activitys.each { activity ->
	def intent_filter =  activity['intent-filter']
	if(intent_filter != null){
		def action = intent_filter.action
		boolean hadMainAction = false
		boolean hadCatagory = false
		action.each { item ->
			//println item['@android:name']
			if(item['@android:name'] == 'android.intent.action.MAIN'){
				hadMainAction = true
			}
		}
		def category = intent_filter.category
		//println category['@android:name']
		if(category['@android:name'] == 'android.intent.category.LAUNCHER'){
			hadCatagory = true
		}
		if(hadMainAction && hadCatagory){
			println "${activity['@android:name']} is main activity"
			return
		}
	}
	println activity['@android:name']
}