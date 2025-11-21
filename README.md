
📘 Projeto PecaFácil
Sistema full-stack para gerenciamento de peças, desenvolvido utilizando Angular, Spring Boot e PostgreSQL.

📑 Índice
1. Sobre o Projeto
2. Tecnologias Utilizadas
3. Arquitetura do Projeto
4. Pré-requisitos
5. Configuração do Ambiente
6. Instalação do Frontend
7. Instalação do Backend
8. Rodando o Projeto
9. Fluxo de Trabalho (Git Flow)
10. Comandos Úteis
11. Banco de Dados
12. Contribuição
13. Licença

⭐ Sobre o Projeto
O PecaFácil é uma aplicação destinada exclusivamente ao gerenciamento de peças e produtos

🛠 Tecnologias Utilizadas
Frontend: Angular, TypeScript, JavaScript
Backend: Java 21, Spring Boot
Banco de Dados: PostgreSQL

🏛 Arquitetura do Projeto
PI-Pe-aFacil/
    pecafacil/
    pecafacil-frontend/
    README.md

📦 Pré-requisitos
Node.js, Angular CLI, Java 21, Maven, PostgreSQL, Git

⚙ Configuração do Ambiente
1. Fazer o fork do projeto do joão
2. Clone o projeto em seu computador, exemplo:
   git clone https://github.com/brainner0/PI-Pe-aFacil.git
3. Fazer a branch develop (não é ideal mexer na main ou na master, faça as alterações em uma branch local de dev e depois suba para master/main no momento correto)
  git checkout -b develop

🗄 Banco de Dados
Lembrar de criar a base
  CREATE DATABASE pecafacil...

🌐 Instalação do Frontend
Na pasta do Front executar
  npm install

🖥 Instalação do Backend
Na pasta do backend executar
  mvn clean install

▶ Rodando o Projeto
Pasta back:
  mvn spring-boot:run
Pasta front:
  npm start


🔁 Dicas para o Git Flow

git add . para adicionar alterações a serem enviadas no GitHub
git commit -m "MENSAGEM" -> escreva as mensagens das alterações
git push origin develop -> envia o commit para a branch que vc criou que é a de desenvolvedor.




🤝 Contribuição
Fork → branch → commit → push → Pull Request




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
12. [Licença](#-licença)

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
- Spring Boot 3.5.x
- Spring Web
- Spring Data JPA
- Hibernate

### **Banco de Dados**
- PostgreSQL 14+
- DBeaver (opcional)

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

Clone o projeto:

```bash
git clone https://github.com/brainner0/PI-Pe-aFacil.git
```

Crie a branch de desenvolvimento:

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
```bash
mvn spring-boot:run
```
Acessar:  
`http://localhost:8080`

### **Frontend**
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

---

## 🗄 **Banco de Dados**

Criar banco:

```sql
CREATE DATABASE pecafacil
    WITH OWNER = postgres
    ENCODING = 'UTF8';
```

Configuração:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pecafacil
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

Listar tabelas:

```sql
SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';
```

---

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

## 📄 **Licença**

Projeto licenciado sob **MIT License**.



