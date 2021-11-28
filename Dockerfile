FROM azul/zulu-openjdk:11
COPY target/hlsa-lab4-0.0.1-SNAPSHOT.jar hlsa-lab4-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/hlsa-lab4-0.0.1-SNAPSHOT.jar"]