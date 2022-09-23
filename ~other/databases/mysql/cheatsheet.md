# Modifying Data With SQL Cheatsheet

## Adding a Row to a Table


Inserting a single row:

```
INSERT INTO <table> VALUES (<value 1>, <value 2>, ...);
```

This will insert values in the order of the columns prescribed in the schema.

Examples:

```
INSERT INTO users VALUES  (1, "chalkers", "Andrew", "Chalkley");
INSERT INTO users VALUES  (2, "ScRiPtKiDdIe", "Kenneth", "Love");

INSERT INTO movies VALUES (3, "Starman", "Science Fiction", 1984);
INSERT INTO movies VALUES (4, "Moulin Rouge!", "Musical", 2001);
```

Inserting a single row with values in any order:

```
INSERT INTO <table> (<column 1>, <column 2>) VALUES (<value 1>, <value 2>);
INSERT INTO <table> (<column 2>, <column 1>) VALUES (<value 2>, <value 1>);
```

Examples:

```
INSERT INTO users (username, first_name, last_name) VALUES ("chalkers", "Andrew", "Chalkley");
INSERT INTO users (first_name, last_name, username) VALUES  ("Kenneth", "Love", "ScRiPtKiDdIe");

INSERT INTO movies (title, genre, year_released) VALUES ("Starman", "Science Fiction", 1984);
INSERT INTO movies (title, year_released, genre) VALUES ("Moulin Rouge!", 2001,  "Musical");
```






# Querying Relational Databases Cheatsheet

## SQL JOINs

JOINs merge related data from multiple tables together in to result set.

The two most common types of joins are:

* INNER JOIN
* OUTER JOIN


### INNER JOINs

INNER JOINs return rows that match from both tables.

```
SELECT <columns> FROM <table 1> 
    INNER JOIN <table 2> ON <table 1>.<column> = <table 2>.<column>;


SELECT <columns> FROM <table 1> AS <table 1 alias> 
    INNER JOIN <table 2> AS <table 2 alias> ON <table 1 alias>.<column> = <table 2 alias>.<column>;

```

Examples:

```
SELECT product_name, category FROM products
    INNER JOIN product_categories ON products.category_id = product_categories.id;
SELECT products.product_name, product_categories.category FROM products
    INNER JOIN product_categories ON products.category_id = product_categories.id;
SELECT p.product_name, c.category FROM products AS p
    INNER JOIN product_categories AS c ON p.category_id = c.id;        
```

INNER JOINing multiple tables:

```
SELECT <columns> FROM <table 1> 
    INNER JOIN <table 2> ON <table 1>.<column> = <table 2>.<column>
    INNER JOIN <table 3> ON <table 1>.<column> = <table 3>.<column>;
```

Examples:

```
SELECT users.full_name, sales.amount, products.name FROM sales 
        INNER JOIN users ON sales.user_id = users.id
        INNER JOIN products ON sales.product_id = products.id;
```

### OUTER JOINs

There are 3 types of OUTER JOINs:

* LEFT OUTER JOIN - JOINs all matching data and all non-matching rows from the _left_ table in the query
* RIGHT OUTER JOIN - JOINs all matching data and all non-matching rows from the _right_ table in the query
* FULL OUTER JOIN - JOINs all matching data and then all non-matching rows from both tables.

```
SELECT <columns> FROM <left table> 
    LEFT OUTER JOIN <right right> ON <left table>.<column> = <right table>.<column>;


SELECT <columns> FROM <left table> AS <left alias> 
    LEFT OUTER JOIN <right table> AS <right alias> 
        ON <left alias>.<column> = <right alias>.<column>;

```

#### Example

If you wanted to get the product count for every category, 
even categories without products, an `OUTER JOIN` is the best solution. 
The following two examples will yield the same results, however one is a `LEFT OUTER JOIN` and one is a `RIGHT OUTER JOIN`.

```
SELECT categories.name, COUNT(products.id) AS "Product Count" FROM categories
    LEFT OUTER JOIN products ON categories.id = products.category_id;

SELECT categories.name, COUNT(products.id) AS "Products Count" FROM products
    RIGHT OUTER JOIN categories ON categories.id = products.category_id;
```

## Set Operations


Set operations merge data in to one set based on column definitions and the data contained within each column.

The four set operations are:

* UNION
* UNION ALL
* INTERSECT
* EXCEPT

The number of columns need to match. If number of columns don't match it'll result in an error.

```
<query 1> <set operation> <query 2>
SELECT <column> FROM <table 1> <set operation> SELECT <column> FROM <table 2>;
SELECT <column>, <column> FROM <table 1> <set operation> SELECT <column>, <column> FROM <table 2>;
```

### UNION Examples

Unions return all distinct values from both data sets with no duplicates.

Get a list of unique restaurants from both north and south malls.

```
SELECT store FROM mall_south WHERE type = "restaurant"
    UNION 
SELECT store FROM mall_north WHERE type = "restaurant";
```

Get a list of unique classes taught in two schools. Order them by their class name.

```
SELECT evening_class FROM school_1 UNION SELECT evening_class FROM school_2
    ORDER BY evening_class ASC;
```

### UNION ALL

Union all returns all values from both data sets – with duplicates.

Get a list of all names for boys and girls and order them by name.

```
SELECT boy_name AS name FROM boy_baby_names 
    UNION ALL 
SELECT girl_name AS name FROM girl_baby_names
    ORDER by name;
```

### INTERSECT

Returns only values that are in both data sets.

Get list of classes offered in both schools.

```
SELECT evening_class FROM school_1 INTERSECT SELECT evening_class FROM school_2
    ORDER BY evening_class ASC;
```
Get list of restaurants at both mall locations.

```
SELECT store FROM mall_south WHERE type = "restaurant"
    INTERSECT 
SELECT store FROM mall_north WHERE type = "restaurant";
```

### EXCEPT

Returns data from the first data set that's not in the second.

Get a list of local stores in a mall.

```
SELECT store FROM mall
    EXCEPT
SELECT store FROM all_stores WHERE type = "national"
```


## Subqueries

Subqueries are queries within queries. A subquery can also be called an _inner_ query with the "parent" query being called the _outer_ query.

There are two main ways to use a subquery:

1. In an `IN` condition
2. As a derived or temporary table

A subquery in an `IN` condition must only have one column.

```
SELECT <columns> FROM <table 1> WHERE <table 1>.<column> IN (<subquery>);
SELECT <columns> FROM <table 1> 
    WHERE <table 1>.<column> IN (SELECT <a single column> FROM <table 2> WHERE <condition>);
```

Examples:

Get a list of user's names and emails for users who have spent over 100 dollars in a single transaction.

```
SELECT name, email FROM users 
    WHERE id IN (SELECT DISTINCT(user_id) FROM sales WHERE saleAmount > 100);

// OR

SELECT name, email FROM users
    INNER JOIN (SELECT DISTINCT(user_id) FROM sales WHERE saleAmount > 100) AS best_customers
    ON users.id = best_customers.user_id;
```

Get a list of user's names and emails for users who have spent over 1000 dollars in total.

```
SELECT name, email FROM users WHERE id IN (SELECT user_id FROM sales WHERE SUM(saleAmount) > 1000 GROUP BY user_id);

// OR

SELECT name, email, total FROM users 
    INNER JOIN (SELECT user_id, SUM(saleAmount) AS total FROM sales WHERE total > 1000 GROUP BY user_id) AS ultimate_customers
    ON users.id = ultimate_customers.user_id;
```

## Adding Multiple Rows to a Table

Inserting multiple rows in a single statement:

```
INSERT INTO <table> (<column 1>, <column 2>, ...) 
             VALUES 
                    (<value 1>, <value 2>, ...),
                    (<value 1>, <value 2>, ...),
                    (<value 1>, <value 2>, ...);
```

Examples:

```
INSERT INTO users (username, first_name, last_name) 
    VALUES 
                  ("chalkers", "Andrew", "Chalkley"),
                  ("ScRiPtKiDdIe", "Kenneth", "Love");

INSERT INTO movies (title, genre, year_released) 
     VALUES 
                   ("Starman", "Science Fiction", 1984),
                   ("Moulin Rouge!", "Musical", 2001);
```

## Updating All Rows in a Table

An update statement for all rows:

```
UPDATE <table> SET <column> = <value>;
```

The `=` sign is different from an equality operator from a `WHERE` condition. It's an _assignment operator_ because you're assigning a new value to something.

Examples:

```
UPDATE users SET password = "thisisabadidea";
UPDATE products SET price = 2.99;
```

Update multiple columns in all rows:

```
UPDATE <table> SET <column 1> = <value 1>, <column 2> = <value 2>;
```

Examples:

```
UPDATE users SET first_name = "Anony", last_name = "Moose";
UPDATE products SET stock_count = 0, price = 0;
```

## Updating Specific Rows

An update statement for specific rows:

```
UPDATE <table> SET <column> = <value> WHERE <condition>;
```
Examples:

```
UPDATE users SET password = "thisisabadidea" WHERE id = 3;
UPDATE blog_posts SET view_count = 1923 WHERE title = "SQL is Awesome";
```

Update multiple columns for specific rows:

```
UPDATE <table> SET <column 1> = <value 1>, <column 2> = <value 2> WHERE <condition>;
```

Examples:

```
UPDATE users SET entry_url = "/home", last_login = "2016-01-05" WHERE id = 329;
UPDATE products SET status = "SOLD OUT", availability = "In 1 Week" WHERE stock_count = 0;
```

## Removing Data from All Rows in a Table

To delete all rows from a table:

```
DELETE FROM <table>;
```

Examples:

```
DELETE FROM logs;
DELETE FROM users;
DELETE FROM products;
```


##  Removing Specific Rows

To delete specific rows from a table:

```
DELETE FROM <table> WHERE <condition>;
```

Examples:

```
DELETE FROM users WHERE email = "andrew@teamtreehouse.com";
DELETE FROM movies WHERE genre = "Musical";
DELETE FROM products WHERE stock_count = 0;
```


## Transactions


Switch autocommit off and begin a transaction:

```
BEGIN TRANSACTION;
```

Or simply:

```
BEGIN;
```

To save all results of the statements after the start of the transaction to disk:

```
COMMIT;
```

To reset the state of the database to before the beginning  of the transaction:

```
ROLLBACK;
````
