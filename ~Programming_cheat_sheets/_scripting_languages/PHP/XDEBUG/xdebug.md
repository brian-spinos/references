# XDEBUG

- Zend debug VS XDEBUG

- XDEBUG is a php extension

- https://xdebug.org/wizard.php

- http://xdebug.org/download.php (for windows)


###### Contents of php.ini

```php
<?php
    // http://localhost/foo.php
    phpinfo();
?>
```


```
A website to help with the steps to setup xdebug:
https://xdebug.org/wizard.php

You should paste the output of your php.ini file in that website

php in windows:  /c/php/php.exe
php.ini in windows:  /c/php/php.ini
```

###### Mac
```bash
$ brew install php56-xdebug # php version 5.6 ?
$ php -v
$ php --init

# you need to restart apache (httpd)
```


###### Windows
```bash
# install:  http://xdebug.org/download.php


# Not sure about this
# use it as a query string or cookie
# XDEBUG_SESSION=PHPSTORM 

# an alias to restart apache
# you should restart apache (httpd) after making changes
alias restart_apache="/c/Apache24/bin/httpd.exe -k restart"
```
