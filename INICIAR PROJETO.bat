@echo off
setlocal enabledelayedexpansion

REM === Descobrir o diretório onde o .BAT está rodando ===
set SCRIPT_DIR=%~dp0

echo ============================================
echo   Iniciando Projeto PecaFacil
echo   Diretorio base: %SCRIPT_DIR%
echo ============================================
echo.

REM === Caminhos do projeto ===
set BACKEND=%SCRIPT_DIR%pecafacil
set FRONTEND=%SCRIPT_DIR%pecafacil-frontend

REM === Verificar se as pastas existem ===
if not exist "%BACKEND%" (
    echo ERRO: Pasta do backend nao encontrada:
    echo %BACKEND%
    pause
    exit /b
)

if not exist "%FRONTEND%" (
    echo ERRO: Pasta do frontend nao encontrada:
    echo %FRONTEND%
    pause
    exit /b
)

REM === Abrir VS Code na raiz do projeto ===
echo Abrindo VS Code...
start "" code "%SCRIPT_DIR%"

REM === Subir o backend (Spring Boot) ===
echo Iniciando backend...
start "" cmd /k "cd /d "%BACKEND%" && mvn spring-boot:run"

REM === Subir o frontend (Angular) ===
echo Iniciando frontend...
start "" cmd /k "cd /d "%FRONTEND%" && npm start"

REM === Abrir navegador ===
echo Abrindo navegador...
start http://localhost:4200

echo.
echo Projeto iniciado com sucesso.
echo.
pause
