# storm-poc


To run:

```bash 
docker-compose up -f docker-compose.yaml -d
mvn clean install package
java -jar target/storm-hello-world-1.0-jar-with-dependencies.jar | grep Final -B 10 -A 10
```