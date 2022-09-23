# Rsync

https://www.youtube.com/watch?v=qE77MbDnljA


```bash
# Copy the *CONTENTS* of `src`, (NOT the `src` folder itself, only it's contents) 
# folder *INTO* `dest` folder
# you can run this command multiple times, it would duplicate files in `dest`
$ rsync -a src/ dest/


# Copy the `src` folder itself (including its files)  INSIDE `dest` folder
$ rsync -a src dest/


# Test you command like this:
$ rsync -av --dry-run src/ dest/
$ rsync -av --dry-run src dest/



# If the dest folder has files that the src folder does not have, 
# rsync does not touch them, if you want to delete files in the dest 
# folder that are not in the src folder, run this command:
$ rsync -a --delete src/ dest/
$ rsync -a --delete src dest/
```

```bash
# Options:

-v # verbose, tells you what the command is doing

--dry-run # runs a test, without actually copying files 

--delete  # removes files in dest if they are not in the src directory, 
          # be careful, if src is empty, then dest will be emptied also!

-a # (archive) preserve symlinks, permissions, dates, etc...

-z # compress while transfering through network

-P # display progress
```

```bash
# Copying files between computers 
$ rsync -zaP ~/Projects/my_site user@192.168.10.5:~/public/
```


```
# What if files have the same name and different contents?

# What if files have same content, but different names?
```

