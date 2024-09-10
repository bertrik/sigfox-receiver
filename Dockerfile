FROM eclipse-temurin:17.0.12_7-jre-alpine

LABEL maintainer="Bertrik Sikken bertrik@gmail.com"
LABEL org.opencontainers.image.source="https://github.com/bertrik/sigfox-receiver"
LABEL org.opencontainers.image.description="Java process for receiving callback from the Sigfox backend"
LABEL org.opencontainers.image.licenses="MIT"

ADD sigfox-receiver/build/distributions/sigfox-receiver.tar /opt/

WORKDIR /opt/sigfox-receiver
ENTRYPOINT ["/opt/sigfox-receiver/bin/sigfox-receiver"]

