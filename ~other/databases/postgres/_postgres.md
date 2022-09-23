# Postgres

http://www.tutorialspoint.com/postgresql/


```bash
$ rails db

# drop a column
$ ALTER TABLE <table-name> DROP COLUMN <column_name>;

# drop a table
$ DROP TABLE <table-name>;

# list databases
$ \l

# list, discribe tables
$ \d

# list, discribe a table
$ \d <table-name>
```


```sql
DROP TABLE items;

CREATE TABLE items(
    id SERIAL PRIMARY KEY,
    text VARCHAR(255) NOT NULL,
    is_done boolean NOT NULL
);


SELECT * FROM items;

INSERT INTO items (text, is_done) VALUES ('wash car again', false);
INSERT INTO items (text, is_done) VALUES ('wash dishes', false);
INSERT INTO items (text, is_done) VALUES ('wash house', false);

UPDATE users SET password_digest='$2a$10$kuM1Tuxbu5cfNkgeBAX4yupgC8dMQkQVXUhtwTsj1d9gPnJxHdF7q' WHERE id = 1;
```
