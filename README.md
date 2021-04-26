# Carteira de Usuario - API
Consiste em uma API que gerencia o fluxo de transferências entre usuário que podem ser de dois tipos: comuns e logistas.

## Postgres
O banco de dados ORM da solução é o Postgres.

## Setup do ambiente de desenvolvimento
Este projeto utiliza:
* Banco de dados Postgres
* JAVA 11

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

## Controle de usuários
Para o cadastro de usuários será necessário informar os seguintes campos:
* nome _(ex: Lívia Aurich)_;
* email _(ex: liviaaurich2@gmail.com)_;
* senha _(ex: tst123)_;
* cpf _(ex: 134.101.477-05)_ ou cnpj _(ex: 52.794.337/0001-11_).

Vale ressaltar, que ao cadastrar os campos são verificados e sua duplicidade averiguada (email e CPF/CNPJ não podem ser duplicados).

## Controle de transferências
Para a realização da transferência é necessário informar os campos:
* valor _(ex: 10.0)_;
* idBeneficiado _(ex: 2)_.

Vale ressaltar que para a realização de transferências o usuário precisa estar "autenticado".

## Autenticação
Para a realização de transferências, conforme citado, é necessário que o usuário esteja autenticado.
Para tanto, um token pode ser gerado com uma requisição **POST** na **URL**: http://127.0.1.1:8081/oauth/token, passando
pelo body:
* username _(e-mail do usuário criado)_;
* password _(senha do usuário criado)_;
* grant_type _(password)_;

Feito isso, um token é gerado e retornado. Logo, para relizar a transferência agora, basta colar
o token selecionando o tipo _Bearer Token_ em _Authorization_.

## Swagger
Com o ambiente iniciado, o link pode ser acessado: http://localhost:8081/swagger-ui.html#/.

## Propostas de melhorias: 
* Dividir a aplicação em microserviços, tendo um por exemplo, responsável por toda parte de autenticação do usuário;
* Criação de roles para limitar o acesso a transferências quando o usuario for um logista. 