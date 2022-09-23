# Git reset

https://www.atlassian.com/git/tutorials/undoing-changes/git-reset

```bash
$ git remote show origin # list github/bitbucket link


#
# Deleting branches
#

$ git push origin --delete my-branch # delete remote branch
$ git branch -D my-branch # delete local branch


# Git has 3 trees:
    - working directory (new files)
    - staging index     (files added, to be committed)
    - commit history    (committed files, in a git commit)


# IntelliJ merge conflict 3 panels: 
    - from left to right:  my-changes / proposed-changes / their-changes
    - after changes do:
        - git add, git commit 





git reset --soft HEAD
    -> undoes a git commit -- nothing is deleted, but a commit is staged -- moves to "staged index"
    -> like undoing a "git commit"

git reset --mixed HEAD
    -> nothing is deleted -- undoes a git add , files are only in the "working directory" -- keeps Untracked files -- deletes COMMITS after the specified one 
    -> like undoing a "git commit" and a "git add"
    
git reset --hard HEAD 
    -> goes to that commit, deletes EVERYTHING ELSE !!!
    -> like undoing a "git commit" and a "git add" and deleting the files




--soft
Working Directory is left untouched.
The Staging Index is left untouched.


 --mixed:
 The Staging Index is reset 
     to the state of the specified commit. 
 Any changes that have been undone from the Staging Index 
     are moved to the Working Directory. 


--hard
- removes all 3???

```

