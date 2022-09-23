# Docker (simple)

###### Simple and stright forward examples



###### Command help
```bash
$ docker image --help
$ docker container --help
```

###### Images

```bash
# list images
# $ docker image ls -a


# create image
#    - from Dockerfile:
#        $ docker image build --tag foo-image .

# remove image
# $ docker image rm foo-image
```



###### Containers

```bash
# list containers
$ docker container ls -a

# create container
#    - with attached terminal
        $ docker container run -it --name foo-container foo-image /bin/bash
#    - let it run in the background
        $ #...
        
#    - start container with volume
        # it seems that docker does not like relative paths for volumes?
        # docker will create the path in the container if it does NOT exist `/aaa/bbb`
        # you can change the data in the volume in the container and in the host computer, they will sync automatically!
        $ docker container run -it --name foo-container \
          -v ~/Desktop/PROJECTS/DOCKER/may_2018/my_data:/aaa/bbb \
          foo-image /bin/bash



# Re-attach to container:
$ docker container start --attach --interactive foo-container


# Remove container
$ docker container rm foo-container
```


###### Dockerfile example
```bash
FROM ubuntu
MAINTAINER Brian Spinos
ENV foo=bar baz=quux
# RUN mkdir my_app
RUN mkdir -p brian/data/something
WORKDIR /brian/data/something
# COPY /Users/brianspinos777/Desktop/PROJECTS/DOCKER/may_2018/my_data /foo/my_data

# COPY ./my_data /brian/data/something
EXPOSE 80
RUN echo "Hello world"
```

