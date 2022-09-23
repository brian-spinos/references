# Mongo

###### Mongo has no schema
###### Tables are collections
###### Documents are rows

```bash
# Install
$ brew install mongodb

# Start daemon
$ sudo mongod 

# In another window, use mongo
$ mongo

$ help

# current db
$ db 

$ show dbs

$ use myDatabase

$ show collections

$ db.createCollection('myTable')

$ db.myTable.insert({name: "brian", age: 28, dob: new Date(1988, 0, 16), x: {a:1, b:2} }) // January is 0

$ db.myTable.find({name: "brian"})
$ db.myTable.find({"x.a": 1})


$ db.myTable.remove({name: "brian"})
$ db.myTable.remove({"x.a": 1})



$ db.myTable.update(
    {name: "brian"}, 
    {$set: {name: "erich"}},
    {multi: true} // update all matching documents
)

$ exit
```
