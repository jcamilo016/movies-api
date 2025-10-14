@echo off
REM Movies API - Run Script (Windows)
echo 🎬 Starting Movies API...

REM Check if Maven Wrapper exists
if not exist "..\mvnw.cmd" (
    echo ❌ Maven Wrapper not found. Please ensure mvnw.cmd exists in the project root.
    exit /b 1
)

REM Check Java version
for /f "tokens=3" %%i in ('java -version 2^>^&1 ^| findstr /i "version"') do (
    set JAVA_VERSION=%%i
)
set JAVA_VERSION=%JAVA_VERSION:"=%
for /f "tokens=1,2 delims=." %%a in ("%JAVA_VERSION%") do (
    if %%a LSS 21 (
        echo ❌ Java 21 or higher is required. Current version: %JAVA_VERSION%
        exit /b 1
    )
)

echo ✅ Java version: %JAVA_VERSION%

REM Change to project root directory
cd ..

REM Clean and compile
echo 🔧 Cleaning and compiling...
.\mvnw.cmd clean compile

if errorlevel 1 (
    echo ❌ Compilation failed!
    exit /b 1
)

echo ✅ Compilation successful!

REM Run the application
echo 🚀 Starting Spring Boot application...
echo 📱 The application will be available at:
echo    - API: http://localhost:8080/api/v1
echo    - Swagger UI: http://localhost:8080/swagger-ui.html
echo    - H2 Console: http://localhost:8080/h2-console
echo.
echo 💡 Press Ctrl+C to stop the application
echo.

.\mvnw.cmd spring-boot:run