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