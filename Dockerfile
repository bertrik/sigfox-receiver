# Alpine Linux with OpenJDK JRE
FROM adoptopenjdk/openjdk14:jre-14.0.2_12-alpine
MAINTAINER Bertrik Sikken bertrik@gmail.com

ADD sigfox-receiver/build/distributions/sigfox-receiver.tar /opt/

WORKDIR /opt/sigfox-receiver
ENTRYPOINT /opt/sigfox-receiver/bin/sigfox-receiver

