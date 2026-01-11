@echo off
echo ====================================
echo Demarrage du Config Service
echo ====================================
echo.
echo Repertoire: C:\Users\Meskour\Desktop\ArtisaLink-Project\config-service
echo Port: 8888
echo Mode: NATIVE (local)
echo Config repo: C:\Users\Meskour\Desktop\ArtisaLink-Project\artisa-link\config-repo
echo.

cd C:\Users\Meskour\Desktop\ArtisaLink-Project\config-service

echo Nettoyage et compilation...
call mvn clean install -DskipTests

echo.
echo Demarrage du service...
call mvn spring-boot:run

pause
