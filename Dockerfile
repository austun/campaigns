FROM java:8
VOLUME /tmp
ADD target/campaigns-0.0.1-SNAPSHOT.jar campaigns-0.0.1-SNAPSHOT.jar
EXPOSE 8080
RUN bash -c 'touch /campaigns-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb/test", "-Djava.security.egd=file:/dev/./urandom","-jar","/campaigns-0.0.1-SNAPSHOT.jar"]
#ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb/test", "-Djava.security.egd=file:/dev/./urandom","-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005","-jar","/campaigns-0.0.1-SNAPSHOT.jar"]

