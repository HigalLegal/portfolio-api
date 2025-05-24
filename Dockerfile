FROM registry.access.redhat.com/ubi8/ubi-minimal

WORKDIR /app

# Copia o executável
COPY build/*-runner /app/application

# Cria estrutura de diretórios primeiro
RUN mkdir -p /app/META-INF/resources

# Copia os arquivos de recursos
COPY src/main/resources/application*.properties /app/
COPY src/main/resources/META-INF/resources/publicKey.pem /app/META-INF/resources/
COPY src/main/resources/privateKey.pem /app/

# Configura permissões
RUN chmod 775 /app/application

EXPOSE 8080

CMD ["./application", "-Dquarkus.profile=dev"]