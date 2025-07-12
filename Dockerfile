## Use OpenJDK as the base image
#FROM openjdk:17-jdk-slim
#
## Add a volume pointing to /tmp
#VOLUME /tmp
#
## Copy the jar file into the container
#ARG JAR_FILE=target/lean-recipes-*.jar
#COPY ${JAR_FILE} app.jar
#
## Run the jar file
#ENTRYPOINT ["java","-jar","/app.jar"]


# Step 1: Use an official lightweight OpenJDK image
FROM openjdk:17-jdk-slim

# Step 2: Set working directory in the container
WORKDIR /app

# Step 3: Copy the JAR file from host to container
COPY build/libs/leanRecipes-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
