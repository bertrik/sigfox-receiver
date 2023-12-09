FROM eclipse-temurin:11.0.21_9-jre-alpine

LABEL maintainer="Bertrik Sikken bertrik@gmail.com"

ADD sigfox-receiver/build/distributions/sigfox-receiver.tar /opt/

WORKDIR /opt/sigfox-receiver
ENTRYPOINT ["/opt/sigfox-receiver/bin/sigfox-receiver"]

