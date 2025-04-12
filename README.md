# Teste Técnico para Agrotis Informática

Este projeto é uma API REST com operações CRUD que utiliza Spring Boot como backend e Postgres como Banco de Dados.
A aplicação permite a consulta, adição, atualização e remoção de Pessoas, Propriedades e Laboratórios.

É possível rodar a aplicação de duas formas: via Maven ou via docker-compose. 

Em **application.properties** você encontrará a configuração do Banco de Dados.

## Instalação via Github
1. **Clone o repositório:**

   ```bash
   git clone https://github.com/duartemarco/desafio-agrotis.git
    ```

2. **Instale as dependências:**

    ```bash
    mvn clean install
    ```

3. **Rode a aplicação Spring Boot:**

    ```bash
    mvn spring-boot:run
    ```

## Instalação via Docker
1. **Clone o repositório**

   ```bash
   git clone https://github.com/duartemarco/desafio-agrotis.git
    ```
2. **Navegue até compose.yaml e digite no terminal:**
    ```bash
    docker-compose up --build
    ```
