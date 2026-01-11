@echo off
color 0A
title ArtisaLink - Demarrage des Microservices

echo ========================================
echo    ARTISALINK - DEMARRAGE COMPLET
echo ========================================
echo.

:check_postgres
echo [1/3] Verification PostgreSQL...
pg_isready >nul 2>&1
if errorlevel 1 (
    echo [ERREUR] PostgreSQL n'est pas demarre !
    echo Veuillez demarrer PostgreSQL et relancer ce script.
    pause
    exit /b 1
)
echo [OK] PostgreSQL est actif
echo.

:start_config
echo [2/3] Demarrage du Config Service (port 8888)...
echo.
start "Config Service" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\config-service && mvn spring-boot:run"

echo Attente du demarrage du Config Service (30 secondes)...
timeout /t 30 /nobreak > nul

echo Verification du Config Service...
curl -s http://localhost:8888/actuator/health > nul 2>&1
if errorlevel 1 (
    echo [ATTENTION] Le Config Service ne repond pas encore...
    echo Veuillez verifier qu'il a bien demarre avant de continuer.
    pause
)
echo [OK] Config Service demarre
echo.

:start_services
echo [3/3] Demarrage des microservices...
echo.

echo Demarrage User Service (port 8081)...
start "User Service" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\user-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo Demarrage Profile Service (port 8082)...
start "Profile Service" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\profile-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo Demarrage Post Service (port 8083)...
start "Post Service" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\post-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo Demarrage Job Service (port 8084)...
start "Job Service" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\job-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo Demarrage Chat Service (port 8085)...
start "Chat Service" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\chat-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo Demarrage Notification Service (port 8086)...
start "Notification Service" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\notification-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo Demarrage Search Service (port 8087)...
start "Search Service" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\search-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo.
echo Attente du demarrage des services (40 secondes)...
timeout /t 40 /nobreak > nul

echo Demarrage Gateway Service (port 8080)...
start "Gateway Service" cmd /c "cd C:\Users\Meskour\Desktop\ArtisaLink-Project\gateway-service && mvn spring-boot:run"

echo.
echo ========================================
echo    DEMARRAGE TERMINE !
echo ========================================
echo.
echo Tous les services sont en cours de demarrage.
echo.
echo Services:
echo   - Config Service:       http://localhost:8888
echo   - User Service:         http://localhost:8081
echo   - Profile Service:      http://localhost:8082
echo   - Post Service:         http://localhost:8083
echo   - Job Service:          http://localhost:8084
echo   - Chat Service:         http://localhost:8085
echo   - Notification Service: http://localhost:8086
echo   - Search Service:       http://localhost:8087
echo   - Gateway Service:      http://localhost:8080
echo.
echo Accedez aux services via le Gateway: http://localhost:8080
echo.
pause
