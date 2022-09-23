# Docker


```bash
# Docker help:
docker <container/image/volume> <sub-command> --help
# Example: $ docker container run --help


docker image --help
docker container --help
docker volume --help


docker --help
# Commands:
#   attach      Attach to a running container
#   build       Build an image from a Dockerfile
#   commit      Create a new image from a container's changes
#   cp          Copy files/folders between a container and the local filesystem
#   create      Create a new container
#   deploy      Deploy a new stack or update an existing stack
#   diff        Inspect changes to files or directories on a container's filesystem
#   events      Get real time events from the server
#   exec        Run a command in a running container
#   export      Export a container's filesystem as a tar archive
#   history     Show the history of an image
#   images      List images
#   import      Import the contents from a tarball to create a filesystem image
#   info        Display system-wide information
#   inspect     Return low-level information on Docker objects
#   kill        Kill one or more running containers
#   load        Load an image from a tar archive or STDIN
#   login       Log in to a Docker registry
#   logout      Log out from a Docker registry
#   logs        Fetch the logs of a container
#   pause       Pause all processes within one or more containers
#   port        List port mappings or a specific mapping for the container
#   ps          List containers
#   pull        Pull an image or a repository from a registry
#   push        Push an image or a repository to a registry
#   rename      Rename a container
#   restart     Restart one or more containers
#   rm          Remove one or more containers
#   rmi         Remove one or more images
#   run         Run a command in a new container
#   save        Save one or more images to a tar archive (streamed to STDOUT by default)
#   search      Search the Docker Hub for images
#   start       Start one or more stopped containers
#   stats       Display a live stream of container(s) resource usage statistics
#   stop        Stop one or more running containers
#   tag         Create a tag TARGET_IMAGE that refers to SOURCE_IMAGE
#   top         Display the running processes of a container
#   unpause     Unpause all processes within one or more containers
#   update      Update configuration of one or more containers
#   version     Show the Docker version information
#   wait        Block until one or more containers stop, then print their exit codes
```


###### Images

```bash
# Images are snapshots of a system (a picture of the state of the system)


# List images
$ docker image ls
$ docker images
$ docker image ls -a
$ docker images -a


# Create an image, from a Dockerfile in the current folder
$ docker image build --tag <my-new-image-name> .
$ docker image build --tag <my-docker-username>/<name-for-new-image>  . 
# Example: $ docker image build -t brianspinos777/my_foo_image


# Delete an image
$ docker image rm <image>
$ docker rmi <image>


# Tagging images
$ docker image tag <image-id> <my-docker-hub-username>/<my-image-name>:<my-tag-name>
$ docker tag <image-id> <my-docker-hub-username>/<my-image-name>:<my-tag-name>
# Example: $ docker tag 5db5f8471261 ouruser/sinatra:devel
```

###### CONTAINERS

```bash
# Containers are running instances of an image


# list all containers
$ docker container ls -a
$ docker ps -a



# list running containers
$ docker container ls
$ docker ps


# remove stale containers
$ docker stop $(docker ps -a -q)
$ docker rm $(docker ps -a -q)


# Create a new container, but it will not run automatically
$ docker container create -it --name <my-new-container-name> <image-name> <command>
$ docker create -it --name <my-new-container-name> <image-name> <command>
# Example: docker container create -it --name foobar_container foobar_image /bin/bash


# Start a stopped container
$ docker container start <container-id-or-name>
$ docker start <container-id-or-name>
# Example:  docker container start --attach --interactive foobar_container 


# Attach to a running container
$ docker container attach <container-id-or-name>
$ docker attach <container-id-or-name>
# Example: $ docker container attach foobar_container


# Stop a running container
$ docker container stop <container-id-or-name>
$ docker stop <container-id-or-name>


# Restart a container
$ docker container restart <container-id-or-name>
$ docker restart <container-id-or-name>
# Example:  docker container restart foobar_container 


# Exit the running container without stopping container
# CTRL + P + Q 


# Exit the running container 
# and STOP running container! 
# it will not show up in the `docker ps` command, just on the `docker ps -a`
# CTRL + D  


# Run a single command in a running container
$ docker container exec <container-id-or-name> <command> 
$ docker exec <container-id-or-name> <command> 
# Example: docker container exec --interactive --tty  foobar_container pwd


# Kill a running container (it does NOT destroy the container)
$ docker container kill <container-id-or-name>
$ docker kill <container-id-or-name>
# Example: docker container kill foobar_container


# List ports mappings of a running container
$ docker container port <container-id-or-name>
$ docker port <container-id-or-name>
# Example: docker container port foobar_container


# Rename a container
$ docker container rename <container-id-or-name> <new-name>
$ docker rename <container-id-or-name> <new-name>
# Exaple: docker container rename foobar_container foobar_container2


# Show running processes inside a running container
$ docker container top <container-id-or-name>
$ docker top <container-id-or-name>
# Example: docker container top foobar_container


# Show stats of a container
$ docker container stats <container-id-or-name>
$ docker stats <container-id-or-name>
# Example: docker container stats foobar_container


# Revome a container
$ docker container rm <container-id-or-name>
$ docker rm <container-id-or-name>
# Example: docker container rm foobar_container


# Run a command on a NEW container
$ docker container run -it --name <my-new-container-name> <IMAGE> <COMMAND>
$ docker run -it --name <my-new-container-name> <IMAGE> <COMMAND>
# Example: docker container run -it --name bozo foobar_image /bin/bash


# Copy a folder from my computer to a running container:
# cp my_folder_full_path my_running_container:container_folder_full_path
$ docker container cp ~/foobar my-running-container:/foobar


# (WARNING, NOT TESTED)
# Create a new image from a container's changes
$ docker container commit --author "Brian Spinos <brianspinos777@hotmail.com>" <container> <new-image-name>:<tag>
$ docker commit --author "Brian Spinos <brianspinos777@hotmail.com>" <container> <new-image-name>:<tag>
# Example: container commit --author "Brian Spinos <brianspinos777@hotmail.com>" foobar_container foobar_image:123
```

###### UNTESTED

```bash
$ docker container pause <container-id-or-name> 
$ docker container unpause <container-id-or-name> 

# If you want a transient container, 
$ docker container run --rm # will remove the container after it stops.


$ docker logs # gets logs from container. (You can use a custom log driver, but logs is only available for json-file and journald in 1.10).
$ docker inspect # looks at all the info on a container (including IP address).
$ docker events # gets events from container.
$ docker diff # shows changed files in the container's FS.
$ docker stats --all # shows a running list of containers.

# These commands were not tested
$ docker wait <container>
$ docker kill <container>



$ docker container run <image> <command> # Create a container (boot and exit)
$ docker container run --rm  <image> <command> # Create a container (boot and exit) and also deletes it (it is a good practice!)
$ docker container run -it <image> sh # create a container and start a shell session



# run a container based on an image (as a backgroud process)
$ docker container run -it -d --name <my-new-container-name> <image>

# run a container based on an image (with port forwarding)
$ docker container run -it -p 80:80 --name <my-new-container-name> <image>

# run a container based on an image 
# (with a volume, so you can make changes in the host, and see them in the container!)
# NOTICE: If you decide to deploy this image, you need to rebuild it! 
# $ docker build -t my_updated-image .
$ docker container run -it -v <src-path>:<dest-path> --name <my-new-container-name> <image>

# create a container from an image called 'ruby', version '2.3.3',
# and run bash
$ docker container run -it ruby:2.3.3 /bin/bash 


# in a server, use the '-d' option to run it in the background
$ docker container run -it -p 80:3000 <image> 


$ docker container run -d -p 80:80 --name webserver nginx # Then go to http://localhost:80
```

###### DOCKERFILE

```bash
# Example https://docs.docker.com/engine/reference/builder/


# Example #1
FROM ubuntu  # FROM <image>:<tag>
MAINTAINER Brian Spinos 
ENV foo=bar baz=quux
WORKDIR /some_existing_folder_in_the_container
RUN mkdir my_app
COPY folder_from_host folder_in_container # COPY my_folder_full_path container_folder_full_path
EXPOSE 80
RUN echo "Hello world"



# Example #2
FROM ruby:2.3.3
RUN apt-get update -qq && apt-get install -y build-essential libpq-dev nodejs
RUN mkdir /myapp
WORKDIR /myapp
ADD Gemfile /myapp/Gemfile
ADD Gemfile.lock /myapp/Gemfile.lock
RUN bundle install
ADD . /myapp
```

######  Volumes

```bash
docker volume ls


# Create new container and add a volume to it (which can be synced in both ways)
# -v my_folder_full_path:container_folder_full_path  # they should sync each other!!!
# -p myport:container_port
# -d detached
$ docker container run -v ~/foobar:/foobar <image>
# Example: $ docker container run -it --name brian_volume_example_c -v ~/Desktop/foo/brian_data:/brian_data brian_volume_example_i

```

######  Links

```
(digitalocen and nginx loadbalancing)
https://www.youtube.com/watch?v=JBtWxj9l7zM
https://www.youtube.com/watch?v=K6WER0oI-qs

https://www.youtube.com/watch?v=UV3cw4QLJLs
https://www.youtube.com/watch?v=1OLyXJJPBSA (good!)
# https://github.com/wsargent/docker-cheat-sheet
https://hub.docker.com/

youtube tutorial  # get link...
```

 
###### Misc

```
# https://hub.docker.com/

# DockerHub is like github, but for images, so you can create your own images!
# You can use DockerHub to download the image into you server! (so its easy to create multiple instances of your server!)
# You should work with GIT inside your container
# Dockerfile

# The Dockerfile is used to to specify instructions to create an image.
# Images

# An image is a snapshot of a container
# An image is an inert, immutable, file that's essentially a snapshot of a container. 
# Images are created with the build command, and they'll produce a container when started with run. 
# Images are stored in a Docker registry such as registry.hub.docker.com
# $ docker search ubuntu
# $ docker pull <image> # Download images from docker hub
# $ docker images # List downloaded images
```



