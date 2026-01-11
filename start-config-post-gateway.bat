@echo off
color 0E
title Demarrage Config + Post + Gateway

echo ========================================
echo   DEMARRAGE COMPLET
echo ========================================
echo.

echo [Etape 1/3] Demarrage Config Service (port 8888)...
start "Config Service" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\config-service && mvn spring-boot:run"
echo [INFO] Attente de 25 secondes...
timeout /t 25 /nobreak > nul
echo.

echo [Etape 2/3] Demarrage Post Service (port 8083)...
start "Post Service" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\post-service && mvn spring-boot:run"
echo [INFO] Attente de 25 secondes...
timeout /t 25 /nobreak > nul
echo.

echo [Etape 3/3] Demarrage Gateway Service (port 9999)...
start "Gateway Service" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\gateway-service && mvn spring-boot:run"
echo [INFO] Attente de 20 secondes...
timeout /t 20 /nobreak > nul
echo.

echo ========================================
echo   DEMARRAGE TERMINE
echo ========================================
echo.
echo Services demarres :
echo   Config  : http://localhost:8888
echo   Post    : http://localhost:8083
echo   Gateway : http://localhost:9999
echo.
echo ========================================
echo   TESTS
echo ========================================
echo.
echo 1. Test direct du Post Service :
echo    http://localhost:8083/api/posts
echo.
echo 2. Test via le Gateway :
echo    http://localhost:9999/api/posts
echo.
echo Ouvrez votre navigateur ou utilisez curl pour tester !
echo.
pause
