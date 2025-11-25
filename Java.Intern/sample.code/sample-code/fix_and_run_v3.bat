@echo off
cd /d "%~dp0"
title FIX LOI JAVA - PHIEN BAN V4
color 0A
echo ========================================================
echo      FIX LOI TU DONG V4 (DUNG MAVEN WRAPPER)
echo ========================================================

REM --- Kiem tra vi tri chay ---
if not exist "pom.xml" (
    color 0C
    echo.
    echo [LOI NGHIEM TRONG] Khong tim thay file pom.xml!
    echo Ban dang chay file nay sai cho. Vui long COPY file nay vao thu muc:
    echo D:\Dev-Project\Java.Spring\Java.Intern\sample.code\sample-code
    echo.
    pause
    exit /b
)

echo [1/5] Dang tat cac tien trinh Java cu...
taskkill /F /IM java.exe >nul 2>&1

echo [2/5] Dang don dep thu muc target (Clean)...
if exist "target" (
    rmdir /s /q "target"
)

echo [3/5] Dang diet tan goc cac file cau hinh gay loi (MySQL)...
if exist "application.yml" (
    del /F /Q "application.yml"
    echo    -> Da xoa application.yml o thu muc goc.
)
if exist "src\main\resources\application.yml" (
    del /F /Q "src\main\resources\application.yml"
    echo    -> Da xoa src/main/resources/application.yml.
)

echo [4/5] Chuan bi chay...
REM Su dung mvnw.cmd thay vi mvn de dam bao chay duoc
set MAVEN_CMD=mvnw.cmd

if not exist "%MAVEN_CMD%" (
    echo [CANH BAO] Khong tim thay mvnw.cmd, thu dung lenh mvn he thong...
    set MAVEN_CMD=mvn
)

echo [5/5] BAT DAU BUILD VA CHAY UNG DUNG...
echo --------------------------------------------------------
REM Chay lenh va giu man hinh
call %MAVEN_CMD% clean spring-boot:run

REM Neu lenh tren bi loi, dong nay se giup man hinh khong bi tat
if %ERRORLEVEL% NEQ 0 (
    color 0C
    echo.
    echo ========================================================
    echo [LOI] CHUONG TRINH GAP SU CO!
    echo Hay chup man hinh nay va gui cho toi de duoc ho tro.
    echo ========================================================
) else (
    echo.
    echo ========================================================
    echo CHUC MUNG! UNG DUNG DA CHAY THANH CONG.
    echo ========================================================
)

pause