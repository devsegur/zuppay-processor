FROM adoptopenjdk/openjdk11:alpine-slim
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY build/libs/*.jar app/
ENTRYPOINT app
CMD java -jar *.jar
EXPOSE 8080
