# Carteira de Usuario - API
Consiste em uma API que gerencia o fluxo de transferências entre usuário que podem ser de dois tipos: comuns e logistas.

## Postgres
O banco de dados ORM da solução é o Postgres.

## Setup do ambiente de desenvolvimento
Este projeto utiliza:
* Banco de dados Postgres
* JAVA 11

Premissas
* JDK 11 instalada

Na pasta **docker** localizado na raiz do repositório existe um docker compose que já prepara os containers necessários para a aplicação rodar.
Utilize o comando abaixo para subir os containers:
```` Java
    $ docker-compose -f postgresql.yml up -d
```` 

Ao subir o container do Postgres, conecte no banco de dados com os seguintes dados:
* Server: localhost:5432
* Authentication: User & Password
* User name: root
* Password: root

Antes de executar o backend, no diretório serviço, execute o seguinte comando:
```` Java
    $ mvn clean install
```` 

O backend pode ser iniciado localmente fora da IDE através do comando dentro do diretório servico:

```` Java
    $ ./mvnw
```` 

## Propostas de melhorias: 
* Dividir a aplicação em microserviços, tendo um por exemplo, responsável por toda parte de autenticação do usuário;
* Criação de roles para limitar o acesso a transferências quando o usuario for um logista. 