# Bash tutorial

http://wiki.bash-hackers.org/syntax/arrays

###### Script example
```bash
#!/bin/bash
echo "Hello World!"
```

###### Make a file executable
```bash
# To make this file executable, in the terminal do:
chmod +x my_script.sh

# Executing the script:
./my_script.sh
```


###### Quick commands
```bash
echo $# # Get the number of arguments passed to the script
!! # Run previous command


; # means run the first command and then the next.
&& # means run the first command and, if it succeeds with a 0 return code, run the next.
|| # means run the first command and, if it fails with a non-zero return code, run the next.

Variable 	Use
$# 	Stores the number of command-line arguments that were passed to the shell program.
$? 	Stores the exit value of the last command that was executed.
$0 	Stores the first word of the entered command (the name of the shell program).
$* 	Stores all the arguments that were entered on the command line ($1 $2 ...).
"$@" 	Stores all the arguments that were entered on the command line, individually quoted ("$1" "$2" ...).

```

###### Variables
```bash
foo="brian"
echo $foo

# variable syntax:
foo
_foo
Foo
FOO
```

###### Functions
```bash
# evaluate code:
$(ls)  # $(some_command)

eval "echo brian"

#------------------------------
# fill in variable with user input:
read VARIABLE


#------------------------------

call_me(){
  first_name=$1
  last_name=$2
  echo "hello Mr. ${last_name}, or would you prefer to be called ${first_name} ???"
}

# call the function with a parameter:
  call_me brian spinos
```

###### More functions

```bash
# $ source <file>
source path/to/file # executes the file

# $ export <variable>
# The export command marks an environment variable 
# to be exported with any newly forked child processes
# If you close the terminal, it will NOT persist.
foo=hello
export foo 

export -p #list env variables

```

###### Loops
```bash
# multiple lines
for foo in $(ls)
  do echo $foo
done

# one liner:
for x in 1 2 3 4 5; do echo $x; done

```

###### Interpolation
```bash
echo "hello ${USER}"
```

###### If statements
```bash

if [ "foo" = "bar" ]
  then
    echo "they are equal"
  else
    echo "they are not equal"
fi
```


###### Arrays
```bash
  # INDEX ARE ZERO BASE
  # http://www.thegeekstuff.com/2010/06/bash-array-tutorial/

  names=(brian ana erich sandra rick)
  echo "${names[@]}"          # returns all names
  echo "${names[2]}"          # returns 'erich'

  my_array[my_index]=my_value
  echo ${my_array[my_index]}      # returns 'my_value'


  Names[0]='Debian'
  Names[1]='Red hat'
  Names[2]='Ubuntu'
  Names[3]='Suse'
  Names[age]='25'
  Names[foo]='bar'

  echo ${Names[1]}
  echo ${Names[bar]}    # if index does not exist, it will resturn the last index declared...

  # declare an array:
  declare -a Names=('Debian2' 'Red hat2' 'Red hat2' 'Suse2' 'Fedora2');
  echo ${Names[@]}    # returns all elements
  echo ${#Names[@]}   # return the number of elements
  echo ${#Names[0]}   # returns the number of characters in the element of index '0'
  echo ${Names[@]:3:2}   # returns the element with index '3' and return 2 elements (also counting the element of index '3')
  echo ${Names[2]:0:3}     # with offset
```


###### Dictionaries
```bash
declare -A arr # associative array
arr[name]="brian"
arr[address]="123 foobar"
echo "${arr[name]}" # brian
echo "${arr[address]}" # 123 foobar
```

###### Heredoc
```bash
cat <<foobar


      ...
        sdfsdfsdf
           sdfsdfs
               dfsdf
                  sdfsdf
                     ...



foobar

```

###### Inject code result in file
```bash

cat > file123.txt <<EOF
$(echo brian)
EOF
```

###### Stuff to cover

```bash
export foo
...

reserved words
```


###### Custom commands with options
```bash
my_command(){
  for ((i=1;i<=$#;i++));
  do
  #-------------------------------------------------
  if [ ${!i} = "-f" ] || [ ${!i} = "--file" ];
  then
    ((i++))
    var1=${!i};
    echo "-f or --file is ${var1}";
  #-------------------------------------------------
  elif [ ${!i} = "-n" ] || [ ${!i} = "--name" ];
  then
    ((i++))
    var2=${!i};
    echo "-n or --name is ${var2}";
  #-------------------------------------------------  
  else
    echo "whaaat?";
  fi
  #-------------------------------------------------
done;
}

my_command --name "sandra"

```
