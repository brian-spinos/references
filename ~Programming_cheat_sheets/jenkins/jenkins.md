# Jenkins 

###### Download jenkins, macOs on the left side of the website:
```
https://jenkins.io/download/
```


###### Install it


###### Go to http://localhost:8080/

###### Start jenkins server from the command-line
```bash
$ java -jar /Applications/Jenkins/jenkins.war --httpPort=8080
```


###### Copy path to password from the jenkins app: (example)
```bash
$ sudo cat /Users/Shared/Jenkins/Home/secrets/initialAdminPassword
```

###### Jenkins might display a message saying its offline, so do:
```bash
# use http, instead of https
$ subl /Users/Shared/Jenkins/Home/hudson.model.UpdateCenter.xml 
```

###### Go to http://localhost:8080/restart

###### click "Install suggested plugins"

###### Fill form to create user/password
```
admin admin
```


###### Shutdown?
```
http://localhost:8080/exit
# confirm POST request

# This works!
$ sudo launchctl unload /Library/LaunchDaemons/org.jenkins-ci.plist
```

```bash
# to find jenkins process
$ ps -e | grep jenkins 
```
