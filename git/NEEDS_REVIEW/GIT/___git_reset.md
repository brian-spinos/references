# GIT Reset

```bash
# git reset: moves the HEAD pointer

# Moves the HEAD pointer, and all changes after HEAD are staginged
git reset --soft HEAD~1 

# Moves the HEAD pointer, and all changes after HEAD are unstaged
git reset --mixed HEAD~1 

# Moves the HEAD pointer, and all changes after HEAD wiped away
git reset --hard HEAD~1

# git reflog -> records the movement of the HEAD

# like an 'undo' button
# HEAD@{0} is the commit that you are now
# git reset --soft HEAD@{1}
```
