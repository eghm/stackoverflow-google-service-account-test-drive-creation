# stackoverflow-google-service-account-test-drive-creation
Project created for Stack Overflow question How to resolve Google Service Account Team Drive creation error of insufficientFilePermissions? [https://stackoverflow.com/questions/50595511/how-to-resolve-google-service-account-team-drive-creation-error-of-insufficientf](https://stackoverflow.com/questions/50595511/how-to-resolve-google-service-account-team-drive-creation-error-of-insufficientf)

I am attempting to create a Google Team Drive using my Service Account but am receiving a insufficientFilePermissions error. Does anyone know how to create a Google Team Drive using a Service Account or can identify what I am doing wrong here?

I've provided the error, Java code for SOTeamDriveCreate, and a pom.xml. One should copy their Service Account credential json file to ServiceAccount.json in the same directory as the pom file. Then execute:

> mvn clean compile ; mvn exec:java -Dexec.mainClass=SOTeamDriveCreate

The insufficientFilePermissions error I would like to resolve:
```java
java.lang.reflect.InvocationTargetException
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:497)
    at org.codehaus.mojo.exec.ExecJavaMojo$1.run(ExecJavaMojo.java:297)
    at java.lang.Thread.run(Thread.java:745)
Caused by: com.google.api.client.googleapis.json.GoogleJsonResponseException: 403 Forbidden
{
    "code" : 403,
    "errors" : [ {
        "domain" : "global",
        "message" : "The user does not have sufficient permissions for this file.",
        "reason" : "insufficientFilePermissions"
    } ],
    "message" : "The user does not have sufficient permissions for this file."
}
    at com.google.api.client.googleapis.json.GoogleJsonResponseException.from(GoogleJsonResponseException.java:146)
    at com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest.newExceptionOnError(AbstractGoogleJsonClientRequest.java:113)
    at com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest.newExceptionOnError(AbstractGoogleJsonClientRequest.java:40)
    at com.google.api.client.googleapis.services.AbstractGoogleClientRequest$1.interceptResponse(AbstractGoogleClientRequest.java:321)
    at com.google.api.client.http.HttpRequest.execute(HttpRequest.java:1065)
    at com.google.api.client.googleapis.services.AbstractGoogleClientRequest.executeUnparsed(AbstractGoogleClientRequest.java:419)
    at com.google.api.client.googleapis.services.AbstractGoogleClientRequest.executeUnparsed(AbstractGoogleClientRequest.java:352)
    at com.google.api.client.googleapis.services.AbstractGoogleClientRequest.execute(AbstractGoogleClientRequest.java:469)
    at SOTeamDriveCreate.createTeamDrive(SOTeamDriveCreate.java:110)
    at SOTeamDriveCreate.main(SOTeamDriveCreate.java:103)
    ... 6 more
```    