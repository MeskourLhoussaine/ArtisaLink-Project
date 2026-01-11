@echo off
color 0A
title Redemarrage Propre - Config + Post Service

echo ========================================
echo   REDEMARRAGE PROPRE
echo ========================================
echo.

echo [Etape 1/5] Arret de tous les services Java...
for /f "tokens=2" %%a in ('tasklist ^| findstr /i "java.exe"') do (
    taskkill /PID %%a /F > nul 2>&1
)
echo [OK] Services arretes
timeout /t 3 /nobreak > nul
echo.

echo [Etape 2/5] Nettoyage du Config Service...
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\config-service
call mvn clean > nul 2>&1
echo [OK] Config Service nettoye
echo.

echo [Etape 3/5] Nettoyage du Post Service...
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\post-service
call mvn clean > nul 2>&1
echo [OK] Post Service nettoye
echo.

echo [Etape 4/5] Demarrage du Config Service...
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\config-service
start "Config Service - Port 8888" cmd /c "mvn spring-boot:run"
echo [INFO] Config Service en cours de demarrage...
echo [INFO] Attente de 30 secondes pour le demarrage complet...
timeout /t 30 /nobreak

echo.
echo Verification du Config Service...
curl -s http://localhost:8888/actuator/health > nul 2>&1
if errorlevel 1 (
    echo [ATTENTION] Le Config Service ne repond pas encore
    echo Veuillez attendre quelques secondes supplementaires
) else (
    echo [OK] Config Service demarre et actif !
)
echo.

echo [Etape 5/5] Demarrage du Post Service...
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\post-service
start "Post Service - Port 8083" cmd /c "mvn spring-boot:run"
echo [INFO] Post Service en cours de demarrage...
echo.

echo ========================================
echo   DEMARRAGE TERMINE
echo ========================================
echo.
echo Services demarres :
echo   Config Service : http://localhost:8888
echo   Post Service   : http://localhost:8083
echo.
echo Surveillez les fenetres de logs pour verifier que tout fonctionne.
echo.
echo Pour tester le Config Service :
echo   http://localhost:8888/post-service/default
echo.
pause
