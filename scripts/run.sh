#!/bin/bash

# Movies API - Run Script
echo "🎬 Starting Movies API..."

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
./mvnw clean compile

if [ $? -ne 0 ]; then
    echo "❌ Compilation failed!"
    exit 1
fi

echo "✅ Compilation successful!"

# Run the application
echo "🚀 Starting Spring Boot application..."
echo "📱 The application will be available at:"
echo "   - API: http://localhost:8080/api/v1"
echo "   - Swagger UI: http://localhost:8080/swagger-ui.html"
echo "   - H2 Console: http://localhost:8080/h2-console"
echo ""
echo "💡 Press Ctrl+C to stop the application"
echo ""

./mvnw spring-boot:run