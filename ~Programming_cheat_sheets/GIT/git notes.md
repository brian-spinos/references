# GIT Notes

```bash
$ git pull # if there are new commits in the remote repo
```

- Conflict if you have 2 commits in the same level with differences
```
e.g: if your colleague pushes some commits, and you did some local changes and committed, 
then pulled, there will be conflict, (if you push it will be rejected, if you pull it will conflict)
```

- The conflict happens if two people work in the same line and commit

- So pull, change the file, and commit, and pull again 
  (but you will have your changes, and the repo their changes are kept, why?…. 
  oh but now you push and your local and remote are the same now!!!)
