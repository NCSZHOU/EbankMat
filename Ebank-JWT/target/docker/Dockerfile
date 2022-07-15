FROM java:8
VOLUME /tmp

ADD Ebank-JWT-1.0.jar /Ebank-JWT.jar

EXPOSE 8081

ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/Ebank-JWT.jar" ]