FROM registry.access.redhat.com/ubi8/ubi-minimal:8.8-1072 AS builder
WORKDIR /build

COPY . .
RUN ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true

FROM registry.access.redhat.com/ubi8/ubi-minimal:8.8-1072
WORKDIR /app

COPY --from=builder /build/build/*-runner /app/application
COPY --from=builder /build/src/main/resources/application*.properties /app/

RUN chown -R 1001:0 /app && \
    chmod -R g+rwX /app && \
    chmod 775 /app/application

EXPOSE 8080
USER 1001

CMD ["./application", "-Dquarkus.profile=prod"]