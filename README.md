# react-thymeleaf

## Set environment variables
Generated here - [Link](https://myaccount.google.com/apppasswords)
```
MAIL_PASSWORD: test 
```

## Building the application into runnable JAR
```
mvn clean package
```
### Build image and pushto github registry ghcr.io 
Need to set the credentials in the .m2/settings.xml file 
or use:  
-Djib.to.auth.username=username  
-Djib.to.auth.password  

```
mvn clean package jib:build -B -ntp -P dev
```
### Image not changed - Deploy to development namespace
```
mvn clean package k8s:build k8s:resource k8s:apply -B -ntp -P dev
```
### Full deployment
```
mvn clean package jib:build k8s:build k8s:resource k8s:apply -B -ntp -P dev
```
## Native compile 
When compiling the project using the native build tools, it take a long time. It is recommended to compile  
locally first(Running on the JVM using AOT bytecode), and  run it locally, before building the full image 

### Native compile locally 
```
mvn -Pnative -DskipTests package
java -Dspring.aot.enabled=true -jar ./target/react-thymeleaf-1.0.0.jar
```

### Native compile using buildpacks 
```
mvn clean -Pnative -DskipTests spring-boot:build-image
```
### Native executable 
```
mvn clean -Pnative native:image 
```