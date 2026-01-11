@echo off
color 0B
title Config + Post Service

echo ========================================
echo  Demarrage Config et Post Service
echo ========================================
echo.

echo [1/2] Demarrage du Config Service...
start "Config Service (8888)" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\config-service && mvn spring-boot:run"

echo Attente du Config Service (25 secondes)...
timeout /t 25 /nobreak > nul

echo.
echo [2/2] Demarrage du Post Service...
start "Post Service (8083)" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\post-service && mvn spring-boot:run"

echo.
echo ========================================
echo  Services en cours de demarrage
echo ========================================
echo.
echo Config Service: http://localhost:8888
echo Post Service:   http://localhost:8083
echo.
echo Verifiez les fenetres ouvertes pour les logs.
echo.
pause
