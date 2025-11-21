# 📘 **Projeto PecaFácil**

Sistema **full-stack** para gerenciamento de peças, desenvolvido utilizando **Angular**, **Spring Boot** e **PostgreSQL**.

---

## 📑 **Índice**

1. [Sobre o Projeto](#-sobre-o-projeto)
2. [Tecnologias Utilizadas](#-tecnologias-utilizadas)
3. [Arquitetura do Projeto](#-arquitetura-do-projeto)
4. [Pré-requisitos](#-pré-requisitos)
5. [Configuração do Ambiente](#-configuração-do-ambiente)
6. [Instalação do Frontend](#-instalação-do-frontend-angular)
7. [Instalação do Backend](#-instalação-do-backend-spring-boot)
8. [Rodando o Projeto](#-rodando-o-projeto)
9. [Fluxo de Trabalho (Git Flow)](#-fluxo-de-trabalho-git-flow)
10. [Banco de Dados](#-banco-de-dados)
11. [Contribuição](#-contribuição)

---

## ⭐ **Sobre o Projeto**

O **PecaFácil** é uma aplicação destinada ao gerenciamento de produtos, construída com uma arquitetura moderna, modular e escalável, com foco em organização, performance e expansão futura.

---

## 🛠 **Tecnologias Utilizadas**

### **Frontend**
- Angular 16+
- TypeScript
- HTML5 / CSS3
- Bootstrap

### **Backend**
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate

### **Banco de Dados**
- PostgreSQL 14+
- DBeaver

### **Ferramentas**
- VS Code
- Maven
- Node.js
- Git

---

## 🏛 **Arquitetura do Projeto**

```
PI-Pe-aFacil/
│
├── pecafacil/              → Backend (Spring Boot)
│   ├── src/main/java/…     → Código-fonte Java
│   ├── src/main/resources/ → application.properties
│   └── pom.xml
│
├── pecafacil-frontend/     → Frontend (Angular)
│   ├── src/                → Componentes Angular
│   ├── angular.json
│   └── package.json
│
└── README.md               → Documentação do projeto
```

---

## 📦 **Pré-requisitos**

Instale antes:

- Node.js 18+
- Angular CLI
- Java JDK 21
- Maven 3.9+
- PostgreSQL
- Git

---

## ⚙ **Configuração do Ambiente**

a. Fazer o fork do projeto do joão

b. Clone o projeto:

    ```bash
    git clone https://github.com/brainner0/PI-Pe-aFacil.git
    ```

Crie a branch de desenvolvimento:

  Fazer a branch develop (não é ideal mexer na main ou na master, faça as alterações em uma branch local de dev e depois suba para master/main no momento correto)

  ```bash
  git checkout -b develop
  ```

---

## 🌐 **Instalação do Frontend (Angular)**

```bash
cd pecafacil-frontend
npm install
```

---

## 🖥 **Instalação do Backend (Spring Boot)**

```bash
cd pecafacil
mvn clean install
```

---

## ▶ **Rodando o Projeto**

### **Backend**
Pasta back:
```bash
mvn spring-boot:run
```
Acessar:  
`http://localhost:8080`

### **Frontend**
Pasta front:
```bash
npm start
```
Acessar:  
`http://localhost:4200`

---

## 🔁 **Fluxo de Trabalho (Git Flow)**

Nunca trabalhar na `main`/`master`.

Criar branch de desenvolvimento:

```bash
git checkout -b develop
```

### **Comandos úteis**

```bash
git add .
git commit -m "Mensagem descritiva"
git push origin develop
```
git add . para adicionar alterações a serem enviadas no GitHub

git commit -m "MENSAGEM" -> escreva as mensagens das alterações

git push origin develop -> envia o commit para a branch que vc criou que é a de desenvolvedor.

---

## 🗄 **Banco de Dados**

Criar banco:

```sql
CREATE DATABASE pecafacil
    WITH OWNER = postgres
    ENCODING = 'UTF8';
```

Configuração:

** FIQUE ATENTO COM A SENHA DO SEU BANCO **

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pecafacil
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```



## 🤝 **Contribuição**

1. Fork
2. Nova branch:
```bash
git checkout -b minha-feature
```
3. Commit
4. Push
5. Pull Request

---




