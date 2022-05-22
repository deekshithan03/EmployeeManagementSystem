FROM openjdk:11-ea-11-jdk
EXPOSE 8081
ADD target/EmployeeManagementSystem-0.0.1-SNAPSHOT.jar EmployeeManagementSystem.jar
ENTRYPOINT ["java","-jar","EmployeeManagementSystem.jar"]
