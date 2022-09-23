# GIT Stash

```bash
# Stash some changes
$ git stash save -u "msg"  # the -u keeps untracked files! awesome! (same as --include-untracked)

# Now you should have a clean state

# Re-apply saved stash
git stash apply stash@{0}

# Now drop the stash (it is NOT automatically deleted)
$ git stash drop stash@{0}

# see all stash entries
$ git stash list  

# It seems you can NOT see the untracked files content 
# (but dont worry, you will be able to restore them!)
$ git stash show stash@{0}
```
