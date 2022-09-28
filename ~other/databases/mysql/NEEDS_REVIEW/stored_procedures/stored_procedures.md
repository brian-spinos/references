# Stored procedures

###### This code was NOT tested yet

```sql
-- Stored procedures (MYSQL)


-- Creating a stored procedure
USE `world`;
DROP procedure IF EXISTS `getCityName`;

DELIMETER $$
USE `world`$$
CREATE PROCEDURE `getCityName` (IN CITY_ID INT, OUT CITY_NAME VARCHAR(255))
BEGIN
SELECT Name INTO CITY_NAME
FROM city
WHERE ID = CITY_ID;
END$$

DELIMETER;


-- calling a stored procedure
set @CITY_ID=1;
CALL `world`.`getCityName`(@CITY_ID, @CITY_NAME);
select @CITY_NAME;
```
