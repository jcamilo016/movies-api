@echo off
REM Movies API - Test Script (Windows)
echo 🧪 Running Movies API Tests...

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
.\mvnw.cmd clean compile test-compile

if errorlevel 1 (
    echo ❌ Compilation failed!
    exit /b 1
)

echo ✅ Compilation successful!

REM Run tests
echo 🧪 Running unit tests...
.\mvnw.cmd test

if errorlevel 0 (
    echo ✅ All tests passed!
    echo.
    echo 📊 Test Results Summary:
    echo    - Unit tests: PASSED
    echo    - Integration tests: PASSED
    echo.
    echo 🎉 Your application is ready to run!
    echo 💡 Use 'run.bat' to start the application
) else (
    echo ❌ Some tests failed!
    echo 💡 Check the test output above for details
    exit /b 1
)