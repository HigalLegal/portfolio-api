FROM registry.access.redhat.com/ubi8/ubi-minimal:8.10

WORKDIR /app

COPY build/*-runner /app/application

RUN chmod 775 /app/application

RUN chown 1001:root /app/application
USER 1001

EXPOSE 8080

CMD ["./application", "-Dquarkus.profile=prod"]