# grep 

> grep is for searching strings in file lines

###### usage:
```bash
$ grep -i <what-so-search> <files>
```

###### brian.txt
```
1. well, hello there
2. my name is brian
3. and this is a tutorial
4. hello Hello HeLLo
5. foo
6. bar
7. baz, hello
```

###### Example
```bash
$ grep -i hello brian.txt # -i is for insensitive
```

###### Output:
```
1. well, hello there
4. hello Hello HeLLo
7. baz, hello
```
