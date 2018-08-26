FROM maven:3.5.4-jdk-8 as builder
WORKDIR /app
COPY . /app
RUN mvn package

FROM openjdk:8u171-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/kas-test.jar .

#On startup JVM tries to detect the number of available CPU cores and the amount of RAM to adjust its internal
#parameters (like the number of garbage collector threads to spawn) accordingly.

#When container is run with limited CPU/RAM, standard system API, used by JVM for probing, will return host-wide values.
#This can cause excessive CPU usage and memory allocation errors with older versions of JVM.

#Inside Linux containers, recent versions of OpenJDK 8 can correctly detect container-limited number of CPU cores
#by default. To enable the detection of container-limited amount of RAM the following options can be used:
#-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap
# Using -XX:MaxRAMFraction=1 , we are using almost all the available memory as the max heap

CMD ["java","-XshowSettings:vm","-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap","-XX:MaxRAMFraction=1","-Djava.security.egd=file:/dev/./urandom","-jar","/app/kas-test.jar"]
