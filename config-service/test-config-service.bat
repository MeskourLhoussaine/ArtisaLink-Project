@echo off
echo ====================================
echo Test du Config Service en mode Native
echo ====================================
echo.

echo Verification que le config-service est demarre...
curl -s http://localhost:8888/actuator/health > nul 2>&1
if errorlevel 1 (
    echo [ERREUR] Le config-service ne repond pas sur le port 8888
    echo Veuillez demarrer le config-service avant de lancer ce test.
    pause
    exit /b 1
)

echo [OK] Config-service est demarre
echo.

echo ====================================
echo Test des configurations des services
echo ====================================
echo.

echo 1. Configuration post-service (default)
curl -s http://localhost:8888/post-service/default | jq . 2>nul || curl -s http://localhost:8888/post-service/default
echo.
echo.

echo 2. Configuration user-service (default)
curl -s http://localhost:8888/user-service/default | jq . 2>nul || curl -s http://localhost:8888/user-service/default
echo.
echo.

echo 3. Configuration profile-service (dev)
curl -s http://localhost:8888/profile-service/dev | jq . 2>nul || curl -s http://localhost:8888/profile-service/dev
echo.
echo.

echo 4. Configuration job-service (prod)
curl -s http://localhost:8888/job-service/prod | jq . 2>nul || curl -s http://localhost:8888/job-service/prod
echo.
echo.

echo ====================================
echo Tests termines !
echo ====================================
pause
