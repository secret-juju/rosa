FROM openjdk:12-alpine
COPY ./build/libs/*.jar rosa.jar
ENTRYPOINT ["java", "-Xmx200m", "-jar", "-Duser.timezone=Asia/Seoul", "/rosa.jar"]