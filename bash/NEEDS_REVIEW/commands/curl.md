# curl

curl is linux's web browser!

###### usage
```bash
curl -d '{"foo": "bar"}' -H "content-type: application/json" http://localhost:8080

```





###### Curl REST API user for SpringBoot:

```bash
-i # headders and response
-I # headders without response


# index
$ curl http://localhost:8182/api/users/index -i 

# show
$ curl http://localhost:8182/api/users/17 -i 

# create 
$ curl http://localhost:8182/api/users/ -i -X POST -H "Content-Type: application/json" -d '{"name": "Erich", "salary": 200000, "team_name": "Foobar"}'

# update 
$ curl http://localhost:8182/api/users/17 -i -X PATCH -H "Content-Type: application/json" -d '{"id": 17, "name": "Erich 2"}'

# destroy 
$ curl http://localhost:8182/api/users/17 -i -X DELETE -H "Content-Type: application/json" -d '{"id": 17}'
```


###### Example:
```bash
$ curl google.com
# <HTML><HEAD><meta http-equiv="content-type" content="text/html;charset=utf-8">
# <TITLE>301 Moved</TITLE></HEAD><BODY>
# <H1>301 Moved</H1>
# The document has moved
# <A HREF="http://www.google.com/">here</A>.
# </BODY></HTML>

$ curl google.com -I
# HTTP/1.1 301 Moved Permanently
# Location: http://www.google.com/
# Content-Type: text/html; charset=UTF-8
# Date: Sat, 31 Dec 2016 20:34:41 GMT
# Expires: Mon, 30 Jan 2017 20:34:41 GMT
# Cache-Control: public, max-age=2592000
# Server: gws
# Content-Length: 219
# X-XSS-Protection: 1; mode=block
# X-Frame-Options: SAMEORIGIN

```


###### curl options

https://www.youtube.com/watch?v=WxUVU0b95Oc

```bash
$ curl http://localhost:5000 # GET request


-i # headders and response

-I headders without response

-d "name=brian&age=29"  # POST request with data

-X PUT -d "name=brian&age=29"  # PUT request with data

-X DELETE  # DELETE request

-u user:password

-o foo.jpeg # download to this file
```
