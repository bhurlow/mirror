FROM openjdk:8-jdk
RUN apt-get update
RUN apt-get install -y graphicsmagick
RUN mkdir /mirror
WORKDIR /mirror
ADD target/mirror.jar /mirror/target/mirror.jar
ADD bin /mirror/bin
RUN mkdir /app
WORKDIR /app
CMD java -cp /mirror/target/mirror.jar:$PWD mirror.cli
