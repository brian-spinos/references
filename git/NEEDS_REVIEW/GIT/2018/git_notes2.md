
###### stash

```bash
# if you are in the middle of work,
# and you need to work on something else

# you are currently in other-branch
git stash save # same as git stash, it also stashes the stagging area
# git stash save --keep-index # does not stash stagging area
# git stash save --include-untracked # stashes also untracked files
# git stash save "some message"


git checkout master
git pull
# make changes and push

git checkout other-branch
git stash apply # stash@{0} is the default
git stash drop # stash@{0} is the default


git stash list
git stash list --stat # any option of git log can be used here!


git stash show stash@{123}
git stash show stash@{123} --patch # shows file changes within the stash


git stash apply stash@{123}
git stash drop stash@{123}

```
```bash

# same as: git stash apply; git stash drop;
git stash pop


```
```bash

# there could be conflicts

# so do:
git reset --hard HEAD
git stash apply


# conflicts after doing git stash pop: you need to manually drop that stash...



```
```bash

# create branch from stash
# checks out the branch, and
# drops the stash automatically
git stash branch new-branch stash@{0}


```
```bash

git stash clear # delete all stashes
```


###### Reflog

```bash

# limbo commits! in case we did a git reset --hard HEAD

git reflog
# or: git log --walk-reflogs



git reset --hard 1e62 # from the reflog
```
