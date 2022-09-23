# NGINX

[NGINX cheat-sheet](https://gist.github.com/carlessanagustin/9509d0d31414804da03b)

###### https://www.sylvaindurand.org/setting-up-a-nginx-web-server-on-macos/

```bash
# terminal

# Install nginx
brew install nginx 

# Edit configuration
subl /usr/local/etc/nginx/nginx.conf

# Start nginx
sudo nginx

# Stop nginx
sudo nginx -s stop

# Restart Nginx to activate the changes:
sudo nginx -s reload

# http://localhost:80
```


```
$ nginx -h

nginx version: nginx/1.12.0
Usage: nginx [-?hvVtTq] [-s signal] [-c filename] [-p prefix] [-g directives]

Options:
  -?,-h         : this help
  -v            : show version and exit
  -V            : show version and configure options then exit
  -t            : test configuration and exit
  -T            : test configuration, dump it and exit
  -q            : suppress non-error messages during configuration testing
  -s signal     : send signal to a master process: stop, quit, reopen, reload
  -p prefix     : set prefix path (default: /usr/local/Cellar/nginx/1.12.0_1/)
  -c filename   : set configuration file (default: /usr/local/etc/nginx/nginx.conf)
  -g directives : set global directives out of configuration file

```




```bash
# Stop NGINX
sudo nginx -s stop

# Start NGINX with a different configuration file
sudo nginx -c /usr/local/etc/nginx/nginx.conf.default

# Go to http://localhost:8080
```
