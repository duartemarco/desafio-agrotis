# Teste Técnico para Agrotis Informática

Este projeto é uma **API REST** desenvolvida com **Spring Boot** e **PostgreSQL**, que realiza operações **CRUD** para as entidades **Pessoa**, **Propriedade** e **Laboratório**.

Você pode rodar a aplicação de duas formas:
- Usando o **Maven** localmente
- Através do **Docker Compose**

---

## Instalação Local
1. **Clone o repositório:**

   ```bash
   git clone https://github.com/duartemarco/desafio-agrotis.git
   cd agrotis-backend
    ```

2. **Certifique-se que o banco de dados PostgreSQL está rodando e crie um banco chamado:**

    ```bash
    CREATE DATABASE db_agrotis;
    ```

3. **Credenciais**


Você pode utilizar o usuário padrão:
- **Usuário:** `postgres`
- **Senha:** `1234`

Ou configurar suas credenciais com variáveis de ambiente:

- `DATABASE_USERNAME`
- `DATABASE_PASSWORD`


3. **Rode a aplicação:**

    ```bash
    ./mvnw spring-boot:run
    ```
O banco será atualizado automaticamente (ddl-auto=update) com base nas entidades. O AgrotisBackendApplication possui um CommandLineRunner para popular as tabelas de Laboratório e Propriedade.

---

## Instalação via Docker
1. **Clone o repositório**

   ```bash
   git clone https://github.com/duartemarco/desafio-agrotis.git
    ```
2. **Navegue até compose.yaml e digite no terminal:**
    ```bash
    docker-compose up --build
    ```

---

## Endpoints Disponíveis

A API expõe endpoints para cadastro, consulta, atualização e remoção de:

- `/pessoas`
- `/propriedades`
- `/laboratorios`


- Para maior praticidade, utilize a [Collection do Postman disponível aqui](https://drive.google.com/file/d/1xciKbzigKi56di4-PcRaNDva5u68YSlP/view?usp=sharing).
