### chmod changes the permission on a file

### usage:
```bash
$ chmod <user-permission><group-permission><other-users-permission> file

# r = 4
# w = 2
# x = 1

# example:
$ chmod 700 foo.txt # gives the user read, write, execute permission on the file
$ chmod 600 foo.txt # gives the user read, write permission on the file
$ chmod 500 foo.txt # gives the user read, execute permission on the file
$ chmod 400 foo.txt # gives the user read permission on the file
$ chmod 300 foo.txt # gives the user write, execute permission on the file
$ chmod 200 foo.txt # gives the user write permission on the file
$ chmod 100 foo.txt # gives the user execute permission on the file
$ chmod 000 foo.txt # gives the user NO permission on the file

$ chmod -R 744 fileOrFolder # recursive

$ sudo chown -R $(whoami) /someFolder/* # not sure if this is the best approach
```
