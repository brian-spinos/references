# REDIS

#-------------------------

# install redis with brew

#-------------------------

# start server:
$ redis-server
# Ctrl + C # to exit 

# redis console
# the server needs to be running...
$ redis-cli
# Ctrl + C # to exit 

# redis commands: http://redis.io/commands

# get all keys:
$ keys * 

#------------------------- set a single key
# key names are pretty flexible, you can use special characters in the key naming


# Keys
SET name "brian"
GET name # "brian"
DEL name # (nil)  # delete key and value

$ set `123-=~!@#$%^&*()_+[]\;',./{}|:"<>?' foo
$ get `123-=~!@#$%^&*()_+[]\;',./{}|:"<>?' # foo

$ set foo.name erich
$ get foo.name # erich

# set key "name" to value "brian"
$ set name brian  # OK

# get a key
$ get name # "brian"

# get type of "key"
# $ type <key>
$ type name # String

# delete all keys: (not in redis console)
$ redis-cli KEYS "*" | xargs redis-cli DEL

# get members of a set:
$ smembers <a-set>

# help @<group> # example of groups: set, list hash, string


#-------------------------

# hmset <name:of:set> <key> <value> <key> <value> …
$ hmset brian:1234 name brian password foobar123

redis 127.0.0.1:6379> HGETALL brian:1234
# 1) "name"
# 2) "brian"
# 3) "password"
# 4) "foobar123"

# update field
$ HSET brian:1234 password changed_password


redis 127.0.0.1:6379> HGETALL brian:1234
# 1) "name"
# 2) "brian"
# 3) "password"
# 4) "changed_password"

#-------------------------



$ get <string>

#------------------------- Sets
$ SADD myset "Hello"
$ SADD myset "Hello2"
$ SMEMBERS myset
# 1) "Hello2"
# 2) "Hello"

SADD myset item1
SADD myset item2
SADD myset item3
SMEMBERS myset # show all members of set

#------------------------- Hashes
HSET myhash name "brian"
HSET myhash age "28"
HMGET myhash age # "28"
HMGET myhash name # "brian"
HGETALL myhash
# 1) "name"
# 2) "brian"
# 3) "age"
# 4) "28"

HKEYS myhash
# 1) "name"
# 2) "age"

#------------------------- Lists
LPUSH mylist foo
LPUSH mylist bar
LPUSH mylist baz
LRANGE mylist 0 -1 # show all items

#------------------------- Sorted sets
ZADD my_sorted_set 1 item1
ZADD my_sorted_set 2 item2
ZADD my_sorted_set 3 item3
ZADD my_sorted_set 4 item4
ZRANGE my_sorted_set 0 -1 WITHSCORES # show all items

#-------------------------
