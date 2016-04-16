Compile and run with:

```
./gradlew clean build; java -jar build/libs/swarmdemo8-swarm.jar
```

Test the camel route with:

```
http://localhost:8080/camel/hello?name=matt
```

View the Swagger JSON spec at http://localhost:8080/camel/api-docs

Read about this sample project at https://dzone.com/articles/security-considerations-with-camel-http-services
