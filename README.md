# jag-vcrc

[![Lifecycle:Stable](https://img.shields.io/badge/Lifecycle-Stable-97ca00)](https://github.com/bcgov/jag-vcrc)
[![Maintainability](https://api.codeclimate.com/v1/badges/d9bac462571a5327f783/maintainability)](https://codeclimate.com/github/bcgov/jag-vcrc/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/d9bac462571a5327f783/test_coverage)](https://codeclimate.com/github/bcgov/jag-vcrc/test_coverage)

Integration API for Court Clerk Desktop for the webMethods retirement project

### Recommended Tools
* Intellij
* Docker
* Maven
* Java 11
* Lombok

### Application Endpoints

Local Host: http://127.0.0.1:8080

Actuator Endpoint Local: http://localhost:8080/actuator/health

Code Climate: https://codeclimate.com/github/bcgov/jag-vcrc

### Required Environmental Variables

BASIC_AUTH_PASS: The password for the basic authentication. This can be any value for local.

BASIC_AUTH_USER: The username for the basic authentication. This can be any value for local.

ORDS_HOST: The url for ords rest package.

### Optional Environmental Variables
SPLUNK_HTTP_URL: The url for the splunk hec.

SPLUNK_TOKEN: The bearer token to authenticate the application.

SPLUNK_INDEX: The index that the application will push logs to. The index must be created in splunk
before they can be pushed to.

### Building the Application
1) Make sure using java 11 for the project modals and sdk
2) Run ```mvn compile```

### Running the application
Option A) Intellij
1) Set env variables.
2) Run the application

Option B) Jar
1) Run ```mvn package```
2) Run ```cd jag-vcrc-application```
3) Run ```java -jar ./target/vcrc-application.jar $ENV_VAR$```  (Note that $ENV_VAR$ are environment variables)

Option C) Docker
1) Run ```mvn package```
2) Run ```cd jag-vcrc-application```
3) Run ```docker build -t vcrc-application .```
4) Run ```docker run -p 8080:8080 vcrc-application $ENV_VAR$```  (Note that $ENV_VAR$ are environment variables)

### Pre Commit
1) Do not commit \CRLF use unix line enders
2) Run the linter ```mvn spotless:apply```

### JaCoCo Coverage Report
1) Run ```mvn clean verify```
2) Open ```jag-vcrc-code-coverage/target/site/jacoco/index.html``` in a browser
