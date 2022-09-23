# Redis

```bash
# Start server:
$ redis-server
# Ctrl + C # to exit 

# Redis console
# The server needs to be running...
$ redis-cli
```

```bash
# Keys
SET name "brian"
GET name # "brian"
DEL name # (nil)  # delete key and value

# Hashes
HSET myhash name "brian"
HSET myhash age "28"
HMGET myhash age # "28"
HMGET myhash name # "brian"

# Lists
LPUSH mylist foo
LPUSH mylist bar
LPUSH mylist baz
LRANGE mylist 0 -1 # show all items

# Sets
SADD myset item1
SADD myset item2
SADD myset item3
SMEMBERS myset # show all members of set

# Sorted sets
ZADD my_sorted_set 1 item1
ZADD my_sorted_set 2 item2
ZADD my_sorted_set 3 item3
ZADD my_sorted_set 4 item4
ZRANGE my_sorted_set 0 -1 WITHSCORES # show all items
```
