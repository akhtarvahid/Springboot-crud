## Springboot-crud


Docker command to start postgres

`> docker run --name springboot-crud -p 5442:5432 -e POSTGRES_PASSWORD=postgres -d postgres 
`
NOTE: Port 5432 assigned to 5442 to avoid conflict while running together with user-service. Also port updated in application.properties

from 

`> jdbc:postgresql://localhost:5432/postgres
`
To

`> jdbc:postgresql://localhost:5442/postgres
`
