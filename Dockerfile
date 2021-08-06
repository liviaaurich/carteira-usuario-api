# Define a imagem base para a que está sendo criada
FROM openjdk:11-jre-slim

# Define o diretório de trabalho onde as instruções irão rodar
WORKDIR /app

# Define uma variável que pode ser passada em tempo de build
ARG JAR_FILE

COPY target/${JAR_FILE} /app/app.jar

# Informa qual porta o container vai escutar quando estiver rodando
EXPOSE 8081

# Define o comando padrão que vai ser rodado quando o container iniciar
CMD ["java", "-jar", "app.jar"]


## Obs.:
# * docker network create --driver bridge
    # -> Crio uma rede para meus vários containers se conectar a ela e se comunicarem
# * docker container run -d -p 5432:5432 -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root --network carteira-usuario-network --name carteira-postgresql postgres:12.1
    # -> Iniciando um container do postegresql especificando a rede

# Passo a passo quando altero:
    # -> docker container rm carteira-postgresql --force --volumes  (remover o container do banco)
    # -> ./mvnw clean package (gerar um novo jar da aplicação)
    # -> docker image build -t carteira-usuario-api . (builda a nova imagem com as modificações realizadas)
    # -> docker container run -d -p 5432:5432 -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root --network carteira-usuario-network --name carteira-postgresql postgres:12.1
        # (subo um container na minha network com a imagem do banco)
    # -> docker image prune (limpar as imagens none)
    # -> docker container run --rm -p 8081:8081 -e DB_HOST=carteira-postgresql --network carteira-usuario-network carteira-usuario-api
        # (subo a aplicação na minha network criada setando a variável de ambiente DB_HOST)

# Construindo a imagem da aplicação manualmente:
    # -> ./mvnw clean package (gerar um novo jar da aplicação)
    # -> docker image build -t carteira-usuario-api . (builda a nova imagem com as modificações realizadas)

# Construindo a imagem da aplicação com o maven:
    # -> pom.xml linha 261 profile docker
    # -> rodar: ./mvnw package -Pdocker (defino o profile que criei para gerar a imagem)