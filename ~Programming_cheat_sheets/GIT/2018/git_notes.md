# Git notes


```bash
git config --global color.ui true
git config --global user.name "brian spinos"
git config --global user.email "brian@example.com"

git config --list # see configs

git config user.email # see email

```

```bash
#
# Create new repo
#

git init # start git repo
git commit -a -m 'first commit'
git remote add origin https://github.com/Brian/my-project.git
git push -u origin master

```

```bash
#
# push to existing repo
#

git remote add origin https://github.com/Brian/my-project.git
git push -u origin master
```

```bash
git remote -v # list remotes
```

```bash
# 3 trees
WORKING_DIRECTORY,  HEAD, INDEX
```

```bash
# make changes to file
git add .
git commit -m 'changes'

```

```bash
git diff --staged # display changes that were added, compared to HEAD
```

```bash
# unstage a file, reverse of git add
# does NOT blow away changes
$ git reset HEAD foo.html

# blow away changes
git checkout -- foo.html
```

```bash
# add changes to TRACKED files, then commit
# does NOT add new (untracked) files
git commit -a -m "some changes" 
```

```bash
# undo a commit
# reverse of git commit
# so it brings back files to the staging area!
git reset --soft HEAD^
```

```bash
git commit --amend -m 'extra changes'
```

```bash
# undo commit and all changes
git reset --hard HEAD^
```

```bash
git remote add <name> <address>
git remote rm <name>
git push -u <name> <address> # push commits
```

```bash
git clone <address> <custom-folder-name>
```

```bash
# create branch
git branch foo

# create and checkout branch
git checkout -b bar 

# go to branch
git checkout foo

```

```bash
# fast-forward merge:

# - master branch has NO commits
# - foo branch HAS commits

git checkout master
git merge foo
git branch -d foo
```

```bash
# recursive merge

# git reates a merge commit with no files

# - master branch HAS commits
# - foo branch HAS commits

git checkout master
git merge foo
git branch -d foo
```


```bash
# when git push does not work:

git pull
git push

```

```bash
# What git pull does:

git fetch # updates branch 'origin/master'
git merge origin/master # with master

# a recursive merge, or fast-forward merge could happen

# there could also be conflicts

# resolve them, then do

git add .
git commit -m 'resolved conflicts'
git push

```

```bash
# remote branches

git checkout -b foo
git push origin foo
# add commits to branch
git push # pushes branch


# pull remote branches
git pull
git branch # list branches, remote branch is not there yet
git branch -r # list remote branches
git checkout foo # actually pull in remote branch

# delete remote branch
git push origin :foo

# now you need to delete local branch
git branch -d foo 
# or git branch -D foo # to force

```

```bash
git remote show origin # 

```


```bash
# git tags

$ git tag # list tags

$ git checkout v0.0.1 # checkout tag

$ git tag -a v0.0.3 -m "version 0.0.3" # add new tag

$ git push --tags  # push tags

```

```bash
# rebase (puts my changes on top)


# instead of doing pull and push:
$ git fetch
$ git rebase


# conflicts

# fix conflict, the add
git add file.html

# - resolve then do either of the three: 
git rebase --continue
git rebase --skip
git rebase --abort

```

```bash
# rebase on local branches

git checkout other-branch
git rebase master

# in case of conflicts see rebase section

git checkout master
git merge other-branch # will result in a fast-forward merge

```


```bash
git log --pretty=oneline
git log --pretty=format:"%h %ad- %s [%an]"
git log --oneline -p # for patch changes
git log --oneline --stat
git log --oneline --graph
git log --until=1.minute.ago
git log --since=1.day.ago
git log --since=1.hour.ago
git log --since=1.month.ago --until=2.weeks.ago
git log --since=2000-01-01 --until=2012-12-21
```

```bash
git blame index.html --date short
```

```bash
git rm foo.html # remove file 
# git commit -m 'removed file'

git rm --cached dev.logs # stop tracking files that were once tracked
# it will NOT be deleted from your file system
```
