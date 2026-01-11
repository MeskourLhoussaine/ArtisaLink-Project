@echo off
color 0C
title Arret des Services ArtisaLink

echo ========================================
echo  ARRET DE TOUS LES SERVICES
echo ========================================
echo.

echo Recherche et arret des processus Java (Spring Boot)...
echo.

for /f "tokens=2" %%a in ('tasklist ^| findstr /i "java.exe"') do (
    echo Arret du processus %%a...
    taskkill /PID %%a /F > nul 2>&1
)

echo.
echo ========================================
echo  TOUS LES SERVICES ONT ETE ARRETES
echo ========================================
echo.
pause
