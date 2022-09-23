# CouchBase

https://query-tutorial.couchbase.com/tutorial/#1

```sql
/* buckets: users, houses, cars, ... */

SELECT * FROM users
SELECT name, address FROM users
SELECT * FROM users WHERE name = 'brian'
SELECT * FROM users WHERE address.street = '123 foo'
SELECT address.street FROM users /* [{name: 'brian', address: { street: '123 foo'}}, ...] */

```
