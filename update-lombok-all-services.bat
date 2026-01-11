@echo off
color 0E
title Update Lombok in All Services

echo ========================================
echo   MISE A JOUR LOMBOK - TOUS LES SERVICES
echo ========================================
echo.
echo Cette operation va mettre a jour Lombok vers la version 1.18.36
echo dans tous les microservices.
echo.
pause

cd C:\Users\Meskour\Desktop\ArtisaLink-Project

echo.
echo [1/5] Mise a jour User Service...
cd user-service
call mvn versions:set-property -Dproperty=lombok.version -DnewVersion=1.18.36 -DgenerateBackupPoms=false 2>nul
echo [OK] User Service

echo.
echo [2/5] Mise a jour Profile Service...
cd ..\profile-service
echo [OK] Profile Service (deja fait)

echo.
echo [3/5] Mise a jour Post Service...
cd ..\post-service
call mvn versions:set-property -Dproperty=lombok.version -DnewVersion=1.18.36 -DgenerateBackupPoms=false 2>nul
echo [OK] Post Service

echo.
echo [4/5] Mise a jour Job Service...
cd ..\job-service
call mvn versions:set-property -Dproperty=lombok.version -DnewVersion=1.18.36 -DgenerateBackupPoms=false 2>nul
echo [OK] Job Service

echo.
echo [5/5] Mise a jour Chat Service...
cd ..\chat-service
call mvn versions:set-property -Dproperty=lombok.version -DnewVersion=1.18.36 -DgenerateBackupPoms=false 2>nul
echo [OK] Chat Service

cd ..

echo.
echo ========================================
echo   MISE A JOUR TERMINEE
echo ========================================
echo.
echo Lombok a ete mis a jour vers la version 1.18.36 (compatible Java 21)
echo dans tous les services.
echo.
echo IMPORTANT: Nettoyez et recompilez chaque service :
echo   mvn clean install -DskipTests
echo.
pause
