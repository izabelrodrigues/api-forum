FROM ibmjava:11-jdk
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Xmx512m","-jar","-Dspring.profiles.active=${ENV}","-Dserver.port=${PORT}","/app.jar"]