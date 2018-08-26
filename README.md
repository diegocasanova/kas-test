# kas-test

Test application based on Spring Boot 2. It exposes a simple read API. 

## Run

### Maven

```sh
$ mvn spring-boot:run
```
And then go to <a href="http://localhost:8080/">http://localhost:8080</a> to open the Swagger UI page.

### Docker-compose
```sh
$ docker-compose run -d 
```

It will build a docker container with the application and it will launch several containers to support it:

- Redis
- Prometheus: go to <a href="http://localhost:9090/">http://localhost:9090</a>
- Grafana: go to <a href="http://localhost:3000/">http://localhost:3000</a> user: admin, pass: admin. It doesn't have a configured Dashboard, I use the [JVM micrometer dashboard](https://grafana.com/dashboards/4701).
- Hystrix Dashboard to monitor the Hystrix circuits. Go to <a href="http://localhost:9002/hystrix">http://localhost:9002/hystrix</a> and enter the URL *http://kas-test:8080/actuator/hystrix.stream* in the input. 


#### Libraries used
- [Retrofit](https://square.github.io/retrofit/) for Rest client
- [Netflix Hystrix through Spring Cloud](https://cloud.spring.io/spring-cloud-netflix/multi/multi__circuit_breaker_hystrix_clients.html) as circuit breaker.
- [Redis](https://redis.io/) as cache.
- [OpenAPI 3 generator](https://github.com/OpenAPITools/openapi-generator) to generate code from an API spec. 
- [Swagger UI](https://swagger.io/tools/swagger-ui/) to have a live API documentation. 
- [MapStruct](http://mapstruct.org/) to generate mappers between different layer models.
- [Wiremock](http://wiremock.org/) for Integration Test.
- [Micrometer](https://micrometer.io/) for metrics.


#### TODO
- Add sorting functionality
- Improve query parameters validation
- Add Cache metrics
- Configure Prometheus alerts

