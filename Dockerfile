# ========================
# Etapa 1 — Compila o binário nativo
# ========================
FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:24.1-java21 AS build
WORKDIR /workspace

# Copia os arquivos de build primeiro (para melhor cache)
COPY gradle* .
COPY build.gradle .
COPY src ./src

# Compila o projeto nativamente
RUN ./gradlew build -Dquarkus.package.type=native --no-daemon

# ========================
# Etapa 2 — Cria imagem mínima para rodar
# ========================
FROM registry.access.redhat.com/ubi8/ubi-minimal:8.10
WORKDIR /app

# Copia o executável gerado da etapa anterior
COPY --from=build /workspace/build/*-runner /app/application

RUN chmod 775 /app /app/application

# Usuário não-root para segurança
RUN chown 1001:root /app /app/application
USER 1001

EXPOSE 8080

CMD ["./application", "-Dquarkus.profile=prod"]