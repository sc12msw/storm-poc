# storm-poc


To run:

```bash 
docker-compose up -f docker-compose.yaml -d
mvn clean install package
docker exec -it <container of supervisor id> /bin/bash
storm jar /target/storm-hello-world-1.0-jar-with-depedencies.jar uk.tojourn.WordCountTopology
```