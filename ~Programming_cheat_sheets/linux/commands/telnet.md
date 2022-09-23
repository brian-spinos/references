# Telnet


###### Telnet is like a browser


```bash
# terminal

telnet google.com 80


# Now type an HTTP request

GET / HTTP/1.1
Host: google.com

# Press enter twice, to get a response
```

###### Response

```
HTTP/1.1 301 Moved Permanently
Location: http://www.google.com/
Content-Type: text/html; charset=UTF-8
Date: Tue, 18 Jul 2017 21:19:56 GMT
Expires: Thu, 17 Aug 2017 21:19:56 GMT
Cache-Control: public, max-age=2592000
Server: gws
Content-Length: 219
X-XSS-Protection: 1; mode=block
X-Frame-Options: SAMEORIGIN

<HTML><HEAD><meta http-equiv="content-type" content="text/html;charset=utf-8">
<TITLE>301 Moved</TITLE></HEAD><BODY>
<H1>301 Moved</H1>
The document has moved
<A HREF="http://www.google.com/">here</A>.
</BODY></HTML>
```
