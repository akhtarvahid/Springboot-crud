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