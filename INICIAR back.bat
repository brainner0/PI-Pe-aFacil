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


REM === Subir o backend (Spring Boot) ===
echo Iniciando backend...
start "" cmd /k "cd /d "%BACKEND%" && mvn spring-boot:run"

echo.
echo Projeto iniciado com sucesso.
echo.
pause
