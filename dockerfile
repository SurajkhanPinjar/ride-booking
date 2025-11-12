# Use official Java 21 image
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy Gradle build files
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Download dependencies (cached)
RUN ./gradlew dependencies --no-daemon || return 0

# Copy source
COPY . .

# Build the app
RUN ./gradlew clean build -x test --no-daemon

# ============= Run Stage =============
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy only jar from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]