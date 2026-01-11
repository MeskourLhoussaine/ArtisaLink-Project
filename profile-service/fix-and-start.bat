@echo off
color 0C
title Fix Profile Service

echo ========================================
echo   CORRECTION DU PROFILE SERVICE
echo ========================================
echo.

echo [Etape 1/3] Nettoyage du projet...
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\profile-service
call mvn clean
echo [OK] Projet nettoye
echo.

echo [Etape 2/3] Compilation du projet...
call mvn compile
if errorlevel 1 (
    echo [ERREUR] La compilation a echoue
    pause
    exit /b 1
)
echo [OK] Compilation reussie
echo.

echo [Etape 3/3] Demarrage du service...
start "Profile Service - Port 8082" cmd /c "mvn spring-boot:run"
echo [INFO] Profile Service en cours de demarrage...
echo.

echo ========================================
echo   SERVICE DEMARRE
echo ========================================
echo.
echo Le Profile Service est en cours de demarrage sur le port 8082
echo Surveillez la fenetre de logs pour verifier qu'il n'y a pas d'erreurs
echo.
echo Test direct :
echo   http://localhost:8082/api/profiles
echo.
echo Test via Gateway :
echo   http://localhost:9999/api/profiles
echo.
pause
