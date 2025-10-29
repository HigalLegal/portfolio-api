FROM registry.access.redhat.com/ubi8/ubi-minimal

WORKDIR /app

COPY target/*-runner /app/application

RUN mkdir -p /app/META-INF/resources

COPY src/main/resources/application*.properties /app/

RUN chmod 775 /app/application

EXPOSE 8080

CMD ["./application", "-Dquarkus.profile=prod"]