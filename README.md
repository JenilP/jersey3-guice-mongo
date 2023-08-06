A simple example REST web app.

Uses 
 - Embedded Jetty for the HTTP Server
 - Jersey 3.0 for the REST framework
 - Guice and HK2 for dependency injection
 - Jackson for data-binding
 - Logback for logging and log rotation
 - Named property binding for configuration
 - MongoDB for persistence.
 - Lombok to make generation and validation easier

Start app with
```shell
export PROPERTIES_FILE=app.properties
mvn clean install
cd monke-api
mvn clean package jetty:run
```

Sample cURL Requests
```shell
➜ curl -X POST "localhost:8080/fruit" -d '{"name":"tomato", "quantity":101, "type":"vegetable"}' -H "Content-Type: application/json" | jq
{
  "name": "tomato",
  "type": "vegetable",
  "quantity": 101
}

➜ curl -X POST "localhost:8080/fruit" -d '{"quantity":101, "type":"vegetable"}' -H "Content-Type: application/json" | jq 
{
  "message": "Name cannot be null"
}

➜ curl -X POST "localhost:8080/fruit/update" -d '{"name":"tomato", "quantity":52, "type":"fruit"}' -H "Content-Type: application/json" | jq
{
  "name": "tomato",
  "type": "fruit",
  "quantity": 52
}

➜ curl -X GET "localhost:8080/fruit?name=tomato" | jq                                                                                           
{
  "name": "tomato",
  "type": "fruit",
  "quantity": 52
}

➜ curl -X GET "localhost:8080/fruit" | jq
{
  "message": "name is marked non-null but is null"
}

➜ curl -X POST "localhost:8080/fruit" -d '{"name":"banana", "quantity":25, "type":"fruit"}' -H "Content-Type: application/json" | jq
{
  "name": "banana",
  "type": "fruit",
  "quantity": 25
}

➜ curl -X GET "localhost:8080/fruit/all" | jq
[
  {
    "name": "tomato",
    "type": "fruit",
    "quantity": 52
  },
  {
    "name": "banana",
    "type": "fruit",
    "quantity": 25
  }
]

➜ curl -X DELETE "localhost:8080/fruit?name=tomato" | jq

➜ curl -X GET "localhost:8080/fruit?name=tomato" | jq
{
  "message": "tomato doesn't exist"
}

➜ curl -X GET "localhost:8080/fruit/all" | jq               
[
  {
    "name": "banana",
    "type": "fruit",
    "quantity": 25
  }
]

➜ curl -X DELETE "localhost:8080/fruit" | jq            
{
  "message": "name is marked non-null but is null"
}
```

Dependency tree
```shell
[INFO] ------------------------< com.monke:monke-dao >-------------------------
[INFO] Building monke-dao 1.0-SNAPSHOT                                    [2/3]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ monke-dao ---
[INFO] com.monke:monke-dao:jar:1.0-SNAPSHOT
[INFO] +- org.mongodb:mongo-java-driver:jar:3.12.12:compile
[INFO] +- com.google.inject:guice:jar:7.0.0:compile
[INFO] |  +- jakarta.inject:jakarta.inject-api:jar:2.0.1:compile
[INFO] |  +- aopalliance:aopalliance:jar:1.0:compile
[INFO] |  \- com.google.guava:guava:jar:31.0.1-jre:compile
[INFO] |     +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |     +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |     +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |     +- org.checkerframework:checker-qual:jar:3.12.0:compile
[INFO] |     +- com.google.errorprone:error_prone_annotations:jar:2.7.1:compile
[INFO] |     \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.26:compile
[INFO] +- org.slf4j:slf4j-api:jar:2.0.7:compile
[INFO] \- ch.qos.logback:logback-classic:jar:1.4.8:compile
[INFO]    \- ch.qos.logback:logback-core:jar:1.4.8:compile
[INFO] 
[INFO] ------------------------< com.monke:monke-api >-------------------------
[INFO] Building monke-api 1.0-SNAPSHOT                                    [3/3]
[INFO] --------------------------------[ war ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ monke-api ---
[INFO] com.monke:monke-api:war:1.0-SNAPSHOT
[INFO] +- org.glassfish.jersey.containers:jersey-container-servlet:jar:3.1.2:compile
[INFO] |  +- org.glassfish.jersey.containers:jersey-container-servlet-core:jar:3.1.2:compile
[INFO] |  +- org.glassfish.jersey.core:jersey-common:jar:3.1.2:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.glassfish.hk2:osgi-resource-locator:jar:1.0.3:compile
[INFO] |  +- org.glassfish.jersey.core:jersey-server:jar:3.1.2:compile
[INFO] |  |  +- org.glassfish.jersey.core:jersey-client:jar:3.1.2:compile
[INFO] |  |  \- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  \- jakarta.ws.rs:jakarta.ws.rs-api:jar:3.1.0:compile
[INFO] +- org.glassfish.jersey.inject:jersey-hk2:jar:3.1.2:compile
[INFO] |  +- org.glassfish.hk2:hk2-locator:jar:3.0.4:compile
[INFO] |  |  +- org.glassfish.hk2.external:aopalliance-repackaged:jar:3.0.4:compile
[INFO] |  |  \- org.glassfish.hk2:hk2-utils:jar:3.0.4:compile
[INFO] |  \- org.javassist:javassist:jar:3.29.0-GA:compile
[INFO] +- org.glassfish.hk2:guice-bridge:jar:3.0.4:compile
[INFO] |  +- jakarta.inject:jakarta.inject-api:jar:2.0.1:compile
[INFO] |  \- org.glassfish.hk2:hk2-api:jar:3.0.4:compile
[INFO] +- org.glassfish.jersey.media:jersey-media-json-jackson:jar:3.1.3:compile
[INFO] |  +- org.glassfish.jersey.ext:jersey-entity-filtering:jar:3.1.3:compile
[INFO] |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.14.1:compile
[INFO] |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.14.1:compile
[INFO] |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.14.1:compile
[INFO] |  +- com.fasterxml.jackson.module:jackson-module-jakarta-xmlbind-annotations:jar:2.14.1:compile
[INFO] |  \- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.0:compile
[INFO] |     \- jakarta.activation:jakarta.activation-api:jar:2.1.0:compile
[INFO] +- com.google.inject:guice:jar:7.0.0:compile
[INFO] |  +- aopalliance:aopalliance:jar:1.0:compile
[INFO] |  \- com.google.guava:guava:jar:31.0.1-jre:compile
[INFO] |     +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |     +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |     +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |     +- org.checkerframework:checker-qual:jar:3.12.0:compile
[INFO] |     +- com.google.errorprone:error_prone_annotations:jar:2.7.1:compile
[INFO] |     \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] +- org.mongodb:mongo-java-driver:jar:3.12.12:compile
[INFO] +- org.apache.commons:commons-lang3:jar:3.13.0:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.26:compile
[INFO] +- org.slf4j:slf4j-api:jar:2.0.7:compile
[INFO] +- ch.qos.logback:logback-classic:jar:1.4.8:compile
[INFO] |  \- ch.qos.logback:logback-core:jar:1.4.8:compile
[INFO] \- com.monke:monke-dao:jar:1.0-SNAPSHOT:compile
[INFO] ------------------------------------------------------------------------
```