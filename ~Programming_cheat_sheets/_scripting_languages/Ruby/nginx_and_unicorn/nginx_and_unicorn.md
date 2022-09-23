# Nginx and Unicorn

###### learn vagrant and capistrano!

- Install nginx

- sudo service nginx start # start

- nginx has a configurations file that has a
  field for another config file for your rails app!

- nginx comunicates with unicorn by a socket

###### Nginx

- cd /etc/nginx/nginx.conf

```nginx

#...

http{

    #...

    include /etc/nginx/sites-enabled/*;
    # In the above folder, you have a symlink called `default`
    # which symlinks to: /etc/nginx/sites-available/default

}

#...

```


###### Rails configuration for nginx:

```nginx
# my_app/config/nginx.conf (so you can symlink that)

upstream unicorn{
    # same as in the unicorn.rb file
    server: unix:/tmp/unicorn.myapp.sock fail_timeout=0;
}

server{
    listen 80 default;
    # server_name example.com;
    root /vagrant/public;
    try_files $uri/index.html $uri @unicorn # @unicorn could be @foo

    location @unicorn{
        # proxy_pass http://localhost:3000; # pass requests to rails
        proxy_pass http://unicorn; # pass requests to the `upstream unicorn`
    }

    error_page 500 502 503 504 /500.html; # error pages
}

```

- restart nginx

- ngingx should only serve the static files
  and pass dynamic requests to rails



###### unicorn.rb

```ruby
# todo
```

```bash
bundle exec unicorn -c ...
```





