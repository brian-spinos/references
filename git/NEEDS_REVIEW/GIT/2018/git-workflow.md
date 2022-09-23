# Git workflow

```bash
$ git add foo.txt  # reverse: $ git reset foo.txt

$ git commit -m 'message' # reverse: $ git reset --soft HEAD~1

# RESET
$ git reset --soft HEAD~1  # undo commit, changes are stagged
$ git reset --mixed HEAD~1 # undo commit, changes are NOT stagged
$ git reset --hard HEAD~1 # undo commit, changes are discarted
```
