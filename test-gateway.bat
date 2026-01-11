@echo off
color 0B
title Test Gateway Service

echo ========================================
echo   TEST DES ROUTES DU GATEWAY
echo ========================================
echo.
echo Gateway URL: http://localhost:9999
echo.

:check_gateway
echo [1] Verification du Gateway...
curl -s http://localhost:9999/actuator/health > nul 2>&1
if errorlevel 1 (
    echo [ERREUR] Le Gateway ne repond pas sur le port 9999
    echo.
    echo Assurez-vous que :
    echo 1. Le gateway-service est demarre
    echo 2. Il ecoute sur le port 9999
    echo.
    pause
    exit /b 1
)
echo [OK] Gateway est actif
echo.

:test_routes
echo [2] Test des routes...
echo.

echo --- POST SERVICE (http://localhost:9999/api/posts) ---
curl -s http://localhost:9999/api/posts
if errorlevel 1 (
    echo [ERREUR] Route /api/posts ne fonctionne pas
    echo Verifiez que le post-service est demarre sur le port 8083
) else (
    echo [OK] Route /api/posts fonctionne
)
echo.
echo.

echo --- USER SERVICE (http://localhost:9999/api/users) ---
curl -s http://localhost:9999/api/users
if errorlevel 1 (
    echo [ERREUR] Route /api/users ne fonctionne pas
    echo Verifiez que le user-service est demarre sur le port 8081
) else (
    echo [OK] Route /api/users fonctionne
)
echo.
echo.

echo --- PROFILE SERVICE (http://localhost:9999/api/profiles) ---
curl -s http://localhost:9999/api/profiles
if errorlevel 1 (
    echo [ERREUR] Route /api/profiles ne fonctionne pas
    echo Verifiez que le profile-service est demarre sur le port 8082
) else (
    echo [OK] Route /api/profiles fonctionne
)
echo.
echo.

echo --- JOB SERVICE (http://localhost:9999/api/jobs) ---
curl -s http://localhost:9999/api/jobs
if errorlevel 1 (
    echo [ERREUR] Route /api/jobs ne fonctionne pas
    echo Verifiez que le job-service est demarre sur le port 8084
) else (
    echo [OK] Route /api/jobs fonctionne
)
echo.
echo.

echo ========================================
echo   TESTS TERMINES
echo ========================================
echo.
echo Si des routes ne fonctionnent pas :
echo 1. Verifiez que les services correspondants sont demarres
echo 2. Testez directement les services (sans gateway)
echo    Ex: http://localhost:8083/api/posts
echo.
pause
