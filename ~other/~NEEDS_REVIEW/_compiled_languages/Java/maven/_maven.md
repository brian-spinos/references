# Maven

- Maven produces an artifact, like jar war
- Maven can version your artifacts
- Maven has local repo to save the dependencies locally
- Mavan can have inheritances, parent pom.xml


------------------------------------------------------
###### Maven goals: (they are plugins in the super pom???)

```bash
$ mvn clean  # - deletes /target directory
$ mvn compile  # - compiles source code, adds files to /target folder
$ mvn test
$ mvn package # - runs 'compile' command first, then tests, and packages based on the chose package, like JAR
$ mvn install # - runs 'package' command, then installs your jar, in the ~/.m2/repository/ directory locally
$ mvn deploy # - runs the 'install command', then deploys it to some specified repo
jar
run


# you can daisy chain gols:
$ mvn clean package
```
------------------------------------------------------
```bash
export JAVA_HOME=/path/to/jre # pointing to the jre folder
export MAVEN_HOME=/path/to/maven/
```
------------------------------------------------------

###### Tags in pom.xml
```
project
    groupId
    artifactId
    version
    modelVersion - 4.0.0 - version of xml we are using
    packaging - usually 'jar'

    dependencies
        dependency
            groupId
            artifactId
            version
    build - what we want to do to build our code, we can override maven defaults here!
        plugins
            plugin
                groupId
                artifactId
                version
                configuration
                    source
                    target
                executions
                    execution
                        id
                        phase
                        goals
                            goal

        directoryStructure?
    repositories
        repository
            id
            name
            url
            snapshots
                enabled
            releases
                enabled
    pluginRepositories
        pluginRepository
            id
            name
            url
            snapshots
                enabled
            releases
                enabled
```
------------------------------------------------------
###### Plugins (they are tied to phases, but can be overriden)

    - compiler plugins - compiles our code using a specific java version
    - jar plugins - generates the jar?
    - source plugins - attaches source code to jars
    - javadoc plugins - attaches javacods to jar

```
org.apache.maven.plugins
maven-compiler-plugin
maven-jar-plugin
maven-source-plugin
maven-javadoc-plugin
```
------------------------------------------------------

###### Folder structure:
```
my-amazing-app
    src
        main
            java
                Foo.java - maven knows to compile this class
        test
            java - for unit test
    target
    pom.xml
```

------------------------------------------------------
###### Running your maven project:

```bash
$ cd ~/my-amazing-app

# will download a bunch of plugins the first time!
$ mvn clean

# downloads a bunch of other plugins the first time
$ mvn compile

# ===> now we should have a /target directory!

# creates a JAR, (if you chose that packaging...),
# in the /target folder.
# it might download some plugins the first time we run this
$ mvn package
```


------------------------------------------------------
###### Phases

- validate
- compile
- test
- package
- integration-test
- verify
- install
- deploy




------------------------------------------------------


###### Dependencies section

scope:
- test
- provided
- runtime(not needed for compilation, but for my code to run)
- compile(is the default)
- system(very brittle, dont use, use my local jar???)
- import




