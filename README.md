# spring-webflux-essentials
Modelo de Stream com Spring Boot, utilizando webFlux e MySQL

#Link para as aulas (DevDojo)
https://www.youtube.com/watch?v=-Ub0u8mMyZM&list=PL62G310vn6nH5Tgcp5q2a1xCb6CsZJAi7&index=14

#Requerimetos
Docker
   - MySQL
   - K6
   - influxDb
   - grafana
   
Eclipse
  - Java 11
  
Postman

## Comandos ## 
# K6 Docker 
docker-compose run k6 run - < testeK6.js

#Dicas: 
Grafana:
  - Para configurar o Host do banco no grafana deverá ser usado como Host: host.docker.internal:8086/
  - Existem dashboards customizadas para importação no site do grafana. (https://grafana.com/grafana/dashboards/2587)

