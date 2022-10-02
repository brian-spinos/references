### Apache

```bash
# add HTML content to that file
$ touch /var/www/index.html 


# configuration file:
$ cat /etc/apache2/httpd.conf
$ cat /etc/apache2/users/brianspinos777.conf
# DocumentRoot "/Library/WebServer/Documents"

# start apache
$ sudo apachectl start

# stop apache
$ sudo apachectl stop

# go to http://localhost
# It works!

# where that html file is:
$ cat /Library/WebServer/Documents/index.html
```

```bash
# For help
apachectl -h
```




###### MISC

```apache

# Apache config

# $ yum -y install httpd
# $ cd /ect/httpd/conf
# $ service httpd start
# $ apachectl -M # list modules
# $ apachectl configtest # test config file

# Apache supports load balancing

# AllowOverride Node - means that .htaccess file cannot override it!


Listen 0.0.0.0:80
User apache
Group apache
ServerName www.example.com
ErrorLog /var/log/httpd/error.log
LoadModule auth_host_module modules/mod_authz_host.so
LoadModule dir_module modules/mod_dir.so
DirectoryIndex index.html
DocumentRoot /var/www/html
<Directory /var/www/html>
    AllowOverride Node
    Order allow,deny
    allow from 192.168.0.0/24
</Directory>

```
