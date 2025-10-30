FROM quay.io/quarkus/ubi-quarkus-native-image:latest AS build
WORKDIR /workspace
COPY . .
RUN ./gradlew build -Dquarkus.package.type=native --no-daemon

FROM registry.access.redhat.com/ubi8/ubi-minimal
WORKDIR /app

COPY --from=build /workspace/build/*-runner /app/application

RUN chmod 775 /app/application

EXPOSE 8080

CMD ["./application", "-Dquarkus.profile=prod"]
