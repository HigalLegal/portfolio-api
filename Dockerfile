FROM registry.access.redhat.com/ubi8/ubi-minimal:8.8-1072 AS builder
WORKDIR /build

COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
RUN ./gradlew quarkusBuild --no-daemon --refresh-dependencies

COPY src src
RUN ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true

FROM registry.access.redhat.com/ubi8/ubi-minimal:8.8-1072
WORKDIR /app

COPY --from=builder /build/build/*-runner /app/application
COPY src/main/resources/application*.properties /app/

RUN chown -R 1001:0 /app && \
    chmod -R g+rwX /app && \
    chmod 775 /app/application

HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/q/health/live || exit 1

EXPOSE 8080
USER 1001

CMD ["./application", "-Dquarkus.profile=prod"]