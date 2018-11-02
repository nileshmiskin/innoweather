# Innoweather - Weather API consumption demo

##### Innoweather exposes a ReST service that wraps the Open weather map API and forcasts tomorrow's weather, also reporting coolest hour of the day.

### Build pre-requisites
- JDK: Java 1.8

### Technologies & frameworks
- Development: Java, Spring Boot
- Testing: Groovy, Spock
- Build: Gradle

### Steps to build
- Clone the project
```
git clone https://github.com/nileshmiskin/innoweather.git
```

- Generate files for IntelliJ
```
gradlew cleanIdea idea
```
- Generate files for eclipse/STS
```
gradlew cleanEclipse eclipse
```
#### The weather service
- Build the project
```
gradlew clean weather-service:build
```
- Code coverage report

Code coverage reports will be produced under weather-service/build/jacocoHtml directory
```
gradlew weather-service:test weather-service:jacocoTestReport
```
- Then start the weather service
```
gradlew weather-service:bootRun
```
Alternatively, you can also use
```
gradlew bootRun
```
Swagger API documentation is available [here](http://localhost:8080/swagger-ui.html).
#### The sample application
- Build the sample application
```
gradlew sample-app:build
```
- Run the sample application
```
java -jar sample-app\build\libs\innoweather-sample-app-1.0.0.jar com.innoweather.sample.Application
```

#### Functional tests
- Run functional tests.

Ensure that weather-service is started before running functional tests.
Functional test reports will be produced under functional-tests/build/spock-reports directory
```
gradlew -Denable.fts=true functional-tests:test
```
### Contact
nileshmiskin@gmail.com