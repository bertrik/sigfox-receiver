FROM eclipse-temurin:17.0.10_7-jre-alpine

LABEL maintainer="Bertrik Sikken bertrik@gmail.com"

ADD sigfox-receiver/build/distributions/sigfox-receiver.tar /opt/

WORKDIR /opt/sigfox-receiver
ENTRYPOINT ["/opt/sigfox-receiver/bin/sigfox-receiver"]

