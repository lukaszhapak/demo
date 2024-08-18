# DEMO

Demo application

--- 

## TODO:

### java
* design patterns
* streams
* optionals
* create exception and print stack trace
* threads, sync example and more stuff
* memory leak


### spring
* email client
* logs to elk https://www.youtube.com/watch?v=hvYUwUmHB6M
* metrics to elk
* actuator
* aop logging with execution time, logging before execution logging after execution plus execution time
* aop logging in commons
* kafka properties and check if message was sent


### http
* thymeleaf tests
* https://docs.spring.io/spring-framework/reference/integration/rest-clients.html
* http clients examples of fetching list, posting some data, etc.
* rest server tests for all methods
* exception handler returning exception dto
* handler interceptor doing some stuff
* http clients properties, like timeout


### db
* properties
* procedures
* partial index (where name != null)
* jooq
* querydsl
* transactions, what causes rollback, checked unchecked (also in try block)
* criteria api, criteria + meta model, query dsl, specification

#### jpa
* entity manager
* @Query with update (@Modifying) 
* JoinColumn vs mapped by
* jpa lock
* jpa get reference id
* entity graph
* jpa inheritance: discriminator column single table, table per class, joined tables
* n plus one
* create / update timestamp

#### jdbc
* jdbc spring
 
#### mongo
* disable embedded mongo in most of the tests
* @Query like: @Query("{name:'?0'}")
* like operator