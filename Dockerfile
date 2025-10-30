FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:23.1-java21 AS build
WORKDIR /workspace
COPY . .
RUN ./gradlew build -Dquarkus.package.type=native --no-daemon

FROM registry.access.redhat.com/ubi8/ubi-minimal:8.10
WORKDIR /app
COPY --from=build /workspace/build/*-runner /app/application
RUN chmod 775 /app/application && chown 1001:root /app/application
USER 1001
EXPOSE 8080
CMD ["./application", "-Dquarkus.profile=prod"]
