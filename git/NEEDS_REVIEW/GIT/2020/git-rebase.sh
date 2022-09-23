$ git diff master new-branch
# think of master being added first, then on top the new branch, what would be the diff?



$ gco new-branch; git rebase master;
# if same line changes, there is a conflict to fix:
# HEAD changes is comming from master
# do a "git add" on the file u fixed
# then do a "git rebase --continue"
# -- result: master changes go under the changes from new-branch
# then go to master branch and merge new-branch -- it will do Fast-forward merge!




#-----
#rebase in progress; onto 9ecd4fc
#You are currently rebasing branch 'new-branch' on '9ecd4fc'.
#  (fix conflicts and then run "git rebase --continue")
#  (use "git rebase --skip" to skip this patch)
#  (use "git rebase --abort" to check out the original branch)
#
#Unmerged paths:
#  (use "git restore --staged <file>..." to unstage)
#  (use "git add <file>..." to mark resolution)
#	both modified:   foo.txt
