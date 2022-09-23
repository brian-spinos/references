# Yaml

```yml
# Yaml is a strict superset of JSON
# https://learnxinyminutes.com/docs/yaml/
# https://youtu.be/cdLNKUoMc6c


# Comments



field1: value1
my bool: true
my num: 123
my null: null
"field2": value2
"field 3": value3
"some:key": "some:value"
field4:
    {field1: value1, field12: value2} # object
field5:
    {field1: value1, field12: value2} # object



# Nesting is achieved by indentation.
some key with nesting:
    field1: value1
    field2: value2
    field3: value3

    field4:
        field4a: value4a
        field4b: value4b
        field4c: value4c


#
# Arrays
#


my array1:
    - {my bool: true, my num: 123, my null: null, "some:key": "some:value"}
    - {my bool: true, my num: 123, my null: null, "some:key": "some:value"}

my array2:
    - a
    - b
    - c
    - {a: b}

 
my_array3:
    - [a, b, c, {a: b}]



# Date
some date: 2001-12-15T02:59:43.1Z



my string: !!str 123
my num: 123

#
# Anchors can be used to duplicate/inherit properties
#

anchored_content: &repeat_me eeeeeeeee 
other_anchor: *repeat_me # eeeeeeeee


baz: &base
    aaa: bbb
    ccc: ddd

foo: &foo
    <<: *base # name: Everyone has same name
    age: 10

bar: &bar
    <<: *base # name: Everyone has same name
    age: 20
    zzz: qqq

bar2:
    <<: *bar
    age: 20
    



#
# Multi-line text
#



some text: |
    Lorem ipsum bla bla bla
    and more text here

some text: >
    Lorem ipsum bla bla bla
    and more text here




```



###### Equivalent json

```json
{
  "field 3": "value3", 
  "bar2": {
    "age": 20, 
    "aaa": "bbb", 
    "zzz": "qqq", 
    "ccc": "ddd"
  }, 
  "my_array3": [
    [
      "a", 
      "b", 
      "c", 
      {
        "a": "b"
      }
    ]
  ], 
  "baz": {
    "aaa": "bbb", 
    "ccc": "ddd"
  }, 
  "my null": null, 
  "my num": 123, 
  "other_anchor": "eeeeeeeee", 
  "field5": {
    "field12": "value2", 
    "field1": "value1"
  }, 
  "my string": "123", 
  "some date": "2001-12-15 02:59:43", 
  "my bool": true, 
  "bar": {
    "age": 20, 
    "aaa": "bbb", 
    "zzz": "qqq", 
    "ccc": "ddd"
  }, 
  "field2": "value2", 
  "field1": "value1", 
  "my array2": [
    "a", 
    "b", 
    "c", 
    {
      "a": "b"
    }
  ], 
  "field4": {
    "field12": "value2", 
    "field1": "value1"
  }, 
  "my array1": [
    {
      "my bool": true, 
      "my null": null, 
      "my num": 123, 
      "some:key": "some:value"
    }, 
    {
      "my bool": true, 
      "my null": null, 
      "my num": 123, 
      "some:key": "some:value"
    }
  ], 
  "some key with nesting": {
    "field2": "value2", 
    "field3": "value3", 
    "field1": "value1", 
    "field4": {
      "field4c": "value4c", 
      "field4b": "value4b", 
      "field4a": "value4a"
    }
  }, 
  "anchored_content": "eeeeeeeee", 
  "some:key": "some:value", 
  "foo": {
    "age": 10, 
    "aaa": "bbb", 
    "ccc": "ddd"
  }, 
  "some text": "Lorem ipsum bla bla bla and more text here\n"
}

```
