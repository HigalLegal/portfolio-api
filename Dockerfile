# ========================
# Etapa 1 — Compila o binário nativo
# ========================
FROM quay.io/quarkus/ubi-quarkus-native-image:latest AS build
WORKDIR /workspace

# Copia todo o projeto
COPY . .

# Compila o projeto nativamente
RUN ./gradlew build -Dquarkus.package.type=native --no-daemon

# ========================
# Etapa 2 — Cria imagem mínima para rodar
# ========================
FROM registry.access.redhat.com/ubi8/ubi-minimal
WORKDIR /app

# Copia o executável gerado da etapa anterior
COPY --from=build /workspace/build/*-runner /app/application

RUN chmod 775 /app/application

EXPOSE 8080

CMD ["./application", "-Dquarkus.profile=prod"]
