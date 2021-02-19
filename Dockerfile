FROM openjdk:11.0.3-jdk-slim
VOLUME /tmp

# The application's jar file
ARG JAR_FILE=/application/target/application.jar

# Add the application's jar to the container
COPY ${JAR_FILE} root.jar

# Run the jar file
ENTRYPOINT [ \
"java", \
"-XX:MaxRAMPercentage=66", \
"-jar", \
"root.jar" \
]
