# Git Rebase

- It makes the branch you specify its 'base'

```bash
$ git checkout unicors # switch to 'unicorns' branch
$ git rebase master # make it the new base of the 'unicorns' branch
```

1. First, rebase will move the commits of 'unicorns' to a temp area
2. Add commits of master to 'unicorns' branch
3. Then the commits of 'unicorns' that were in the temp area are moved back!, 
   on top of the comments that were added to 'unicorns'

###### Rebase -i (interactive)

```bash
# Gotcha!
$ git log # shows commits from newest to oldest
$ git rebase -i HEAD~1 # in the editor script will show commits from OLDEST to NEWEST
```

- You can manage the commits, reorganize, squash, split up commits in to two...

```bash
# Interactive rebase alters every commit after the one you specify, 
# so in this case: it would alter HEAD~2 and HEAD~1
$ git rebase -i HEAD~3 

# Doesnt do anything
$ git rebase -i HEAD 
```
