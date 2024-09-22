## Springboot-crud


Docker command to start postgres

> docker run --name springboot-crud -p 5442:5432 -e POSTGRES_PASSWORD=postgres -d postgres


NOTE: Port 5432 assigned to 5442 to avoid conflict while running together with user-service. Also port updated in application.properties

from

> jdbc:postgresql://localhost:5432/postgres

To

> jdbc:postgresql://localhost:5442/postgres





AWS RDS configuration steps to follow
1. Create RDS database and pass Endpoint & port to

```js

  jdbc:postgresql://<Endpoint>:<PORT_NUMBER>/postgres

```

2. RDS(VPC security groups) instance should have security-group-rule with below configuration otherwise host connection will fail.

```js

PROTOCOL   PORT   SOURCE
TCP        5432   0.0.0.0/0

```


### How to create and run multiple instances.
1. add this `server.port=${SERVER_PORT}` to application.properties
2. Got to `Run->Edit configurations`
3. Select `ProductServiceApplication` under Spring Boot and click on `copy configuration` as many as you want server(instances).
4. Run all ProductServiceApplication, ProductServiceApplication(1), ProductServiceApplication(2)...etc one by one.
5. Go to http://localhost:8761/ to see all the instances running 
6. And `SERVER_PORT=8080`, `SERVER_PORT=8081`, `SERVER_PORT=8082`...etc inside Environment variables as many instances created
