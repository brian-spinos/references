# Vim

Cheet sheets: https://vim.rtorr.com/

```bash
$ vi foo.txt # edit a file

i  # insert mode
ESC # not insert mode

# up, down, left, right   # use the arrows, duh!
w # go to the beginning of the next word (good for fast forward)
b # go to the beginning of the previous word (good for fast rewind)
ESC :123 # go to line number 123

1yy  # copy 1 line
p  # paste
1dd # delete 1 line

u  # undo
CTRL r  # re-do

ESC  /foo # pattern find, click enter the first time, then click "n" to find the next.
ESC :%s/foo/bar/g #replace all

ESC :e /path/to/file # go to another file

```

###### Goto's

```bash
  Beginning of line:            0
  Beginning of line and append: I
  End of line and append:       A
  Beginning of file:            gg
  Last line:                    G
  Beginning of word in a line:  w
  Beginning of previous word:   b
  Line 34:                      34G
  Append at end of file:        GA
```

###### Cursor

```bash
  Right:  l   3l
  Left:   h    4h
  Up:     k    10k
  Down:   j    23j
```

###### Edit
```bash
  Delete n character on left of cursor:  x   3x
  Delete whole line: dd
  Undo:     u   4u
  Redo: ctrl + r
```

```bash
<ESC> :q           # Quit vim.
<ESC> :q!          # Quit without saving changes i.e. discard changes.
<ESC> :r fileName  # Read data from file called fileName
<ESC> :wq          # Write and quit (save and exit)
<ESC> :w fileName  # Write to file called fileName (save as)
<ESC> :w! fileName # Overwrite to file called fileName (save as forcefully)
```
