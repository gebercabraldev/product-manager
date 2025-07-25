# 🛒 Product Manager

[![Java](https://img.shields.io/badge/Java-17-blue?logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.7-green?logo=springboot)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-14+-dd0031?logo=angular)](https://angular.io/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-%23336791.svg?logo=postgresql\&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-%230db7ed.svg?logo=docker\&logoColor=white)](https://www.docker.com/)
[![GitHub Actions](https://img.shields.io/github/actions/workflow/status/seu-usuario/product-manager/ci.yml?label=CI\&logo=githubactions)](https://github.com/seu-usuario/product-manager/actions)
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](./LICENSE)

---

Sistema **Full Stack** para gestão de produtos, com backend em **Spring Boot** e frontend em **Angular**.
Permite autenticação com JWT e operações completas de CRUD. Ideal para demonstração em portfólios de desenvolvedores Full Stack.

---

## 🚀 Tecnologias Utilizadas

### 🖥️ Backend

* Java 17
* Spring Boot (Web, Security, Data JPA)
* PostgreSQL
* Swagger / OpenAPI
* JWT
* Maven

### 💻 Frontend

* Angular 14+
* Angular Material
* TypeScript
* RxJS
* SCSS

### ⚙️ DevOps e Ferramentas

* Docker
* Git e GitHub
* GitHub Actions (CI/CD)
* Postman

---

## 📸 Capturas de Tela

> *(Adicione prints da UI em Angular e das requisições no Postman, se desejar)*

---

## 🧠 Funcionalidades

* ✅ Cadastro, edição, listagem e remoção de produtos (CRUD)
* 🔐 Autenticação com JWT
* ⚙️ Validações robustas no frontend e backend
* 📑 Documentação da API via Swagger
* 🐳 Executável com Docker

---

## 🛠️ Como Executar Localmente

### ✅ Pré-requisitos

* Java 17
* Node.js + Angular CLI
* Docker (opcional)
* PostgreSQL

---

### ▶️ Passos

#### 1️⃣ Clone o repositório

```bash
git clone https://github.com/seu-usuario/product-manager.git
cd product-manager
```

---

#### 2️⃣ Configure o banco PostgreSQL

##### 🔹 Opção 1: Via Docker

```bash
docker run --name product-db \
  -e POSTGRES_DB=productdb \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=admin \
  -p 5432:5432 \
  -d postgres
```

##### 🔹 Opção 2: Local (manual)

1. Acesse o PostgreSQL e execute:

```sql
CREATE DATABASE productdb;
CREATE USER admin WITH PASSWORD 'admin';
GRANT ALL PRIVILEGES ON DATABASE productdb TO admin;
```

> ⚠️ Você pode alterar as credenciais no `application.properties` se necessário.

---

#### 3️⃣ Execute o backend

```bash
cd backend
./mvnw spring-boot:run
```

> No Windows:

```bash
mvnw.cmd spring-boot:run
```

---

#### 4️⃣ Execute o frontend

```bash
cd ../frontend
npm install
ng serve
```

---

#### 5️⃣ Acesse no navegador

* Frontend: [http://localhost:4200](http://localhost:4200)
* Backend Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 📄 Licença

Este projeto está sob a licença MIT.
Veja o arquivo [LICENSE](./LICENSE) para mais detalhes.

---

### 👨‍💻 Desenvolvido por [Geber Cabral](https://linkedin.com/in/geberdev)

