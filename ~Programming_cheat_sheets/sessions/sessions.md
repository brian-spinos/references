# Sessions

- https://www.justinweiss.com/articles/how-rails-sessions-work/


###### where to store session data:
- Cookie
- cache
- database


###### Cookie
```
storing your sessions in cookies is by far the easiest way to go. 
It doesn’t need any extra infrastructure or setup.
```

###### cache
```
- your cache data and sessions could be fighting for space
```

###### Database
```
client: username and passowrd
server: look it up in the db, if found, create a cookie with userId encrypted, send to client
client: now has access to cookie, and sends it with every request
client: on logout, delete cookie

on login, server creates a session record in the DB, with a randomID, and base64 {userId: 123}, on the data field
client uses that sessionId on the cookie

your cookie only contains a sessionID , the server looks that up in the sessions table in the DB 
```

