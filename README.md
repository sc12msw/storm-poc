# storm-poc


To run:

```bash 
# This runs locally
mvn clean install package
java -jar target/storm-hello-world-1.0-jar-with-dependencies.jar | grep Final -B 10 -A 10
```