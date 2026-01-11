@echo off
color 0E
title Test Config Service

echo ========================================
echo   TEST DU CONFIG SERVICE
echo ========================================
echo.

echo Verification de l'etat du Config Service...
echo URL: http://localhost:8888
echo.

:check_health
echo [1] Test Health Endpoint...
curl -s http://localhost:8888/actuator/health 2>nul
if errorlevel 1 (
    echo.
    echo [ERREUR] Le Config Service ne repond pas !
    echo.
    echo Verifiez que :
    echo 1. Le config-service est demarre
    echo 2. Il ecoute bien sur le port 8888
    echo 3. Il n'y a pas d'erreurs dans les logs
    echo.
    pause
    exit /b 1
)
echo.
echo [OK] Config Service est actif
echo.

:test_configs
echo [2] Test des configurations...
echo.

echo --- Configuration post-service (default) ---
curl -s http://localhost:8888/post-service/default
echo.
echo.

echo --- Configuration user-service (default) ---
curl -s http://localhost:8888/user-service/default
echo.
echo.

echo --- Configuration profile-service (default) ---
curl -s http://localhost:8888/profile-service/default
echo.
echo.

echo --- Configuration job-service (default) ---
curl -s http://localhost:8888/job-service/default
echo.
echo.

echo ========================================
echo   TESTS TERMINES
echo ========================================
echo.
echo Si vous voyez du JSON ci-dessus, le Config Service fonctionne !
echo.
pause
