@echo off
color 0A
title Fix Lombok-MapStruct Compatibility

echo ========================================
echo   FIX LOMBOK + MAPSTRUCT
echo ========================================
echo.
echo Ce script va :
echo 1. Supprimer les fichiers compiles
echo 2. Nettoyer le cache Maven
echo 3. Recompiler le projet avec les nouvelles versions
echo.

cd C:\Users\Meskour\Desktop\ArtisaLink-Project\profile-service

echo [Etape 1/4] Nettoyage des fichiers compiles...
if exist target rmdir /s /q target
echo [OK] Fichiers compiles supprimes
echo.

echo [Etape 2/4] Nettoyage du cache Maven local...
call mvn dependency:purge-local-repository -DactTransitively=false -DreResolve=false
echo [OK] Cache nettoye
echo.

echo [Etape 3/4] Telechargement des nouvelles dependances...
call mvn clean install -U -DskipTests
if errorlevel 1 (
    echo [ERREUR] La compilation a echoue
    echo.
    echo Verifiez les erreurs ci-dessus
    pause
    exit /b 1
)
echo [OK] Compilation reussie !
echo.

echo [Etape 4/4] Demarrage du service...
start "Profile Service - Port 8082" cmd /c "mvn spring-boot:run"
echo [INFO] Service en cours de demarrage...
echo.

echo ========================================
echo   CORRECTION TERMINEE
echo ========================================
echo.
echo Versions mises a jour :
echo   - Lombok: 1.18.24 -^> 1.18.36
echo   - MapStruct: 1.5.2 -^> 1.6.3
echo   - Lombok-MapStruct-Binding: 0.2.0 (nouveau)
echo.
echo Le Profile Service devrait maintenant demarrer sans erreur !
echo.
pause
