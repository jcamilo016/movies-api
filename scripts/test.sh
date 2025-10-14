#!/bin/bash

# Movies API - Test Script
echo "🧪 Running Movies API Tests..."

# Check if Maven Wrapper exists
if [ ! -f "../mvnw" ]; then
    echo "❌ Maven Wrapper not found. Please ensure mvnw exists in the project root."
    exit 1
fi

# Check if Java 21 is available
JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 21 ]; then
    echo "❌ Java 21 or higher is required. Current version: $JAVA_VERSION"
    exit 1
fi

echo "✅ Java version: $(java -version 2>&1 | head -n 1)"

# Change to project root directory
cd ..

# Clean and compile
echo "🔧 Cleaning and compiling..."
./mvnw clean compile test-compile

if [ $? -ne 0 ]; then
    echo "❌ Compilation failed!"
    exit 1
fi

echo "✅ Compilation successful!"

# Run tests
echo "🧪 Running unit tests..."
./mvnw test

if [ $? -eq 0 ]; then
    echo "✅ All tests passed!"
    echo ""
    echo "📊 Test Results Summary:"
    echo "   - Unit tests: PASSED"
    echo "   - Integration tests: PASSED"
    echo ""
    echo "🎉 Your application is ready to run!"
    echo "💡 Use './run.sh' to start the application"
else
    echo "❌ Some tests failed!"
    echo "💡 Check the test output above for details"
    exit 1
fi