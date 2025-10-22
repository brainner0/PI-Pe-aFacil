@echo off
REM Abrir VS Code na raiz do projeto
start cmd /k "cd /d C:\Users\jpmar\Downloads\pecafacil && code .."

REM Backend
start cmd /k "cd /d C:\Users\jpmar\Downloads\pecafacil\pecafacil && mvn spring-boot:run"

REM Frontend
start cmd /k "cd /d C:\Users\jpmar\Downloads\pecafacil\pecafacil-frontend && npm start"

REM Abrir no navegador
start http://localhost:4200
