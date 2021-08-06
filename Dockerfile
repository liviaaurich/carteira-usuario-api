# Define a imagem base para a que está sendo criada
FROM openjdk:11-jre-slim

# Define o diretório de trabalho onde as instruções irão rodar
WORKDIR /app

# Define uma variável que pode ser passada em tempo de build
ARG JAR_FILE

COPY target/${JAR_FILE} /app/app.jar
# Copio também o wait_for_it.sh para ser executado (esse arquivo faz com que um container só suba após seu dependente)
COPY wait-for-it.sh /wait-for-it.sh

# Dizer que o arquivo é executável
RUN chmod +x /wait-for-it.sh

# Informa qual porta o container vai escutar quando estiver rodando
EXPOSE 8081

# Define o comando padrão que vai ser rodado quando o container iniciar
CMD ["java", "-jar", "app.jar"]