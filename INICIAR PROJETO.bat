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

REM === Verificar pastas ===
if not exist "%BACKEND%" (
    echo ERRO: Pasta do backend nao encontrada:
    echo %BACKEND%
    timeout /t 5 >nul
    exit /b
)

if not exist "%FRONTEND%" (
    echo ERRO: Pasta do frontend nao encontrada:
    echo %FRONTEND%
    timeout /t 5 >nul
    exit /b
)

REM === Abrir VS Code ===
echo Abrindo VS Code...
start "" code "%SCRIPT_DIR%"

REM === Subir backend ===
echo Iniciando backend...
start "" cmd /k "cd /d "%BACKEND%" && mvn spring-boot:run"

REM === Subir frontend ===
echo Iniciando frontend...
start "" cmd /k "cd /d "%FRONTEND%" && npm start"

REM === Abrir navegador ===
echo Abrindo navegador...
start http://localhost:4200

echo.
echo Projeto iniciado com sucesso.
echo Este terminal sera fechado.
echo.

REM ===== FECHAR ESTE TERMINAL =====
timeout /t 2 >nul
exit
