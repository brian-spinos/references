# GIT remote repo (without github)


```bash
# On the server

$ cd /srv/git
$ mkdir project.git
$ cd project.git
$ git init --bare

# Initialized empty Git repository in /srv/git/project.git/
```


```bash
# On my computer

$ cd myproject
$ git init
$ git add .
$ git commit -m 'initial commit'
$ git remote add origin git@gitserver:/srv/git/project.git
$ git push origin master

# At this point, the others can clone it down and push changes back up just as easily:

$ git clone git@gitserver:/srv/git/project.git
$ cd project
$ vim README
$ git commit -am 'fix for the README file'
$ git push origin master
```
