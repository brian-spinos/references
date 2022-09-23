# Database design

###### Normalization

```
1NF:
- Each row should have a primary key
- Each column should have one value, not multiple values.
- Each column should be of one type.
```

```
2NF:
- Follow 1NF
- All column data should depend on full primary key and not part.
```

```
3NF:
- Follow 2NF
- No column should depend on other columns.
```

* Do not be hard on avoiding redundancy, if performance is the key
