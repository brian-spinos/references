# Gradle

```groovy
// Gradle

// https://www.baeldung.com/gradle

// $ brew install gradle
// $ gradle -v
// $ gradle # execute gradle
// $ gradle dependencies # displays the dependencies in the terminal


// projects
//     tasks


// simple task (build.gradle):
// $ gradle -q hello
task hello {
    doLast { // could be: `doFirst`
        // ...gradle code...
        println 'Baeldung'
    }
}

//
// task dependency example
//

task helloGradle {
    doLast {
        println 'Hello Gradle!'
    }
}

task fromBaeldung(dependsOn: helloGradle) {
    doLast {
        println "I'm from Baeldung"
    }
}

//
// adding properties
//


task ourTask {
    ext.theProperty = "theValue"
}



//
// plugins
//

plugins {
    id 'application' // core plugin
    id "org.shipkit.bintray" version "0.9.116" // 3rd party plugin
}






//
// java
//

apply plugin: "java" // this plugin has tasks, like 'jar'



//
// junit
//

// https://mvnrepository.com/artifact/junit/junit
testCompile group: 'junit', name: 'junit', version: '4.12'





//
// groovy functions
//

String foo(String bar){
    return bar;
}


// braces are optional
someFunc {
    // this is a closure
    otherFunc "someParam"
}
// someFunc.otherFunc "someParam" // same as above


//
// groovy closures
//

// type is optional
def closureWithOneArg = { String str -> str.toUpperCase() }
assert closureWithOneArg('groovy') == 'GROOVY'



//
// examples, not sure if this is correct
//


repositories {
    mavenCentral()
}

apply plugin: "java"

dependencies {
    compile "org.apache.commons:commons-lang:3.3.2"
    compile "org.apache.commons:commons-lang:3.3.2"
    compile "org.apache.commons:commons-lang:3.3.2"
}










```
