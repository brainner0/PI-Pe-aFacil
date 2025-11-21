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


if not exist "%FRONTEND%" (
    echo ERRO: Pasta do frontend nao encontrada:
    echo %FRONTEND%
    pause
    exit /b
)


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
