# 🛒 Product Manager

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-green?logo=springboot)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-14+-red?logo=angular)](https://angular.io/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-%23336791.svg?logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-%230db7ed.svg?logo=docker&logoColor=white)](https://www.docker.com/)
[![GitHub Actions](https://img.shields.io/github/actions/workflow/status/seu-usuario/product-manager/ci.yml?label=CI&logo=githubactions)](https://github.com/seu-usuario/product-manager/actions)
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](./LICENSE)

---

Sistema **Full Stack** para gestão de produtos, desenvolvido com **Java + Spring Boot** no backend e **Angular** no frontend.  
Ideal para demonstrar domínio em arquitetura de software moderna, autenticação JWT e integração com banco relacional.

---

## 🚀 Tecnologias Utilizadas

### 🖥️ Backend
- Java 17
- Spring Boot (Web, Security, Data JPA)
- PostgreSQL
- JWT para autenticação
- Swagger / OpenAPI
- Maven

### 💻 Frontend
- Angular 14+
- Angular Material
- TypeScript + SCSS
- RxJS

### ⚙️ DevOps e Ferramentas
- Docker
- Git e GitHub
- GitHub Actions (CI/CD)
- Postman

---

## 📸 Capturas de Tela

> *(Adicione aqui imagens do sistema rodando: telas do Angular e requisições no Postman com JWT, por exemplo)*

---

## 🧠 Funcionalidades

- ✅ CRUD de Produtos (Create, Read, Update, Delete)
- 🔐 Autenticação com JWT
- ⚙️ Validações robustas (frontend e backend)
- 📑 Documentação da API com Swagger
- 📦 Docker para facilitar o deploy local

---

## 🛠️ Como Executar Localmente

### ✅ Pré-requisitos
- Java 17
- Node.js + Angular CLI
- Docker
- PostgreSQL

▶️ Passos para rodar o projeto
1️⃣ Clone o repositório:
bash
Copiar
Editar
git clone https://github.com/seu-usuario/product-manager.git
cd product-manager
2️⃣ Configure o banco PostgreSQL
🔹 Opção 1: Usar PostgreSQL via Docker (recomendado)
bash
Copiar
Editar
docker run --name product-db \
  -e POSTGRES_DB=productdb \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=admin \
  -p 5432:5432 \
  -d postgres
🔹 Opção 2: Configuração manual local
Acesse o PostgreSQL (ex: psql, DBeaver, pgAdmin)

Crie o banco de dados:

sql
Copiar
Editar
CREATE DATABASE productdb;
Crie o usuário e a senha (caso não existam):

sql
Copiar
Editar
CREATE USER admin WITH PASSWORD 'admin';
GRANT ALL PRIVILEGES ON DATABASE productdb TO admin;
⚠️ Se precisar alterar credenciais, edite o arquivo src/main/resources/application.properties no backend.

3️⃣ Execute o backend
bash
Copiar
Editar
cd backend
./mvnw spring-boot:run
Ou, se estiver no Windows:

bash
Copiar
Editar
mvnw.cmd spring-boot:run
4️⃣ Execute o frontend
bash
Copiar
Editar
cd ../frontend
npm install
ng serve
5️⃣ Acesse no navegador:
Frontend: 👉 http://localhost:4200

Backend (Swagger): 👉 http://localhost:8080/swagger-ui/index.html

