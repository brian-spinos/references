# Xdebug for windows and vs code

- https://www.youtube.com/watch?v=xME6uHYTcLU&feature=share

```
# PHP versions below 7.0 are not supported.  (BUT I WAS ABLE TO DO IT! using an existing php_xdebug.dll file)
```

```
- WARNING: THIS IS NOT FULLY CORRECT, THIS IS JUST SOME INITIAL NOTES, I STILL NEED TO ORGANIZE THIS...


1. intall xampp

2. configure xampp
    httpd.config (only if you need to change the path wich `http://localhost/` points to...)
        DocumentRoot "C:/xampp/htdocs"
        <Directory "C:/xampp/htdocs">


3. configure xdebug
    index.php >>    <?php echo phpinfo(); ?>
    - copy its contents
    - go to https://xdebug.org/wizard.php
    - paste it there
    - click: 'analize my phpinfo'
    - follow instructions


    ; Local Variables:
    ; tab-width: 4
    ; End:
    [XDebug]
    xdebug.remote_enable = 1
    xdebug.remote_autostart = 1
    ;zend_extension = C:\xampp\php\ext\php_xdebug-2.5.0-7.0-vc14.dll ; prefer the line from  https://xdebug.org/wizard.php
    zend_extension = C:\xampp\php\ext\php_xdebug.dll ; this is what I currently have (hey, it worked!)

4. install vs code

5. configure VS code
    file > preferences > settings
    - paste this in the right panel:


// Place your settings in this file to overwrite the default settings
{
    "window.zoomLevel": 0,
    "window.openFilesInNewWindow": "off",
    "files.autoSave": "afterDelay",
    "editor.wordWrap": "off",
    "window.restoreFullscreen": true,
    "editor.renderIndentGuides": true,
    "editor.mouseWheelZoom": true,
 
    "php.validate.enable": true,
    "php.validate.executablePath": "c:\\xampp\\php\\php.exe",
    "php.executablePath":  "c:\\xampp\\php\\php.exe",
    "php.validate.run": "onType",
    "workbench.welcome.enabled": false,
    "terminal.integrated.shell.windows": "C:\\Program Files\\Git\\bin\\bash.exe"
}



    - then save the file! (CTRL + S ???)
    - install php-debug extension by felixfbecker (in VS Code)



6. install xdebug helper
       google for "xdebug helper", it is a chrome extension

7. test debugger
    - turn on the debugger chromer extension
    - on vs code, click on the "bug" icon in the left sidebar
    - there are other steps here... I will add them latter
```
