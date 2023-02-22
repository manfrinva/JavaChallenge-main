# JavaChallenge-main

## Manual deployment
It's application requires the following:
- Java 8
- maven
- git

### Generates jar File
It's necessary to package the code:
```
mvn install
mvn package
```

### Run unit test
```
mvn test
```

### Run Integration tests
```
mvn clean test-compile failsafe:integration-test
```

## Concourse deployment