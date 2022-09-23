# remove last 2 commits locally
git reset --hard HEAD~2

# undoes the changes from commit abc123, creating a new commit with the changes
git revert abc123


# Undo commit
git reset --soft HEAD~1

# undo git add
git reset foo.txt


#
# commit unwanted changes, then restore them back to original state:
#
// keep track of your original branch name: example: [feature/foobar-branch]
$ git add .
$ gcm "DONT_USE_THIS_COMMIT"
// go to the other branch u want to go to do your stuff...
// now get back to your original branch and do:
$ git reset --mixed <SHA> # SHA of the commit ****BEFORE**** your "DONT_USE_THIS_COMMIT" -- seems that even the untracked files go back to be untracked!!!

