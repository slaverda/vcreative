FROM openjdk:11.0.3-jdk-slim
VOLUME /tmp

# The application's jar file
ARG JAR_FILE=/target/root.jar

# Add the application's jar to the container
COPY ${JAR_FILE} root.jar

# Run the jar file
ENTRYPOINT [ \
"java", \
"-XX:MaxRAMPercentage=66", \
"-jar", \
"-Dcom.sun.management.jmxremote", \
"-Dcom.sun.management.jmxremote.port=1098", \
"-Dcom.sun.management.jmxremote.rmi.port=1098", \
"-Dcom.sun.management.jmxremote.ssl=false", \
"-Dcom.sun.management.jmxremote.authenticate=false", \
"-Dcom.sun.management.jmxremote.local.only=false", \
"-Djava.rmi.server.hostname=127.0.0.1", \
"root.jar" \
]
