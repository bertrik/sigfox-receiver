# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine
MAINTAINER Bertrik Sikken bertrik@gmail.com

ADD sigfox-receiver/build/distributions/sigfox-receiver.tar /opt/

WORKDIR /opt/sigfox-receiver
ENTRYPOINT /opt/sigfox-receiver/bin/sigfox-receiver

