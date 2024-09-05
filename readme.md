# DEMO

Demo application


## java:
* immutable
* kolekcje
* dzialanie hashmap
* w czym trzymac pieniadze
* stream optional, filter map collect flat map / reduce group partition 
* interfejs funkcyjny
* wycieki pamieci
* enum
* equals and hashcode
* async 
* pule watkow
* jak utworzyc watek, thread runnable thread pool executor
* czekaj na rezultat asynca
* comparator vs comparable
* string pool
* exceptions 
* czy mozna catch error
* predicate supplier function bifunction
* debugger
* jvm, class loader, garbage collector, heap
* jvm , jre, jdk

## spring:
* bean, compoment, scope
* transactional
* proxy
* thread safe as singletone
* inject properties
* primary bean
* @resource
* sposoby wstrzykiwania
* aop

## jpa:
* lazy initialization
* entity life cycle
* dto vs entity
* optimistic lock
* exceptions a transactional i rollback
* lazy eager
* jpql
* 1st level cache
* search
* dziedziczenie encji
* cache

## architecture:
* sync vs async
* inbox, saga, 2pc
* cqrs
* cap theorem, PACELC
* acid vs base
* solid
* circuit breaker
* retry
* solid dry kiss law of demeter
* layered arch
* ports and adapter 
* microservices
* review
* code smells

## design patterns:
* creational:
* * singleton
* * factory method
* * builder
* structural:
* * adapter
* * facade
* * proxy
* behavioral:
* * observer
* * strategy

## sql:
* index
* normalizacja
* acid
* where vs having
* identity vs sequence

## nosql:
* base
* przyklady baz i do czego sa
* base

## testing:
* FIRST
* archunit
* tdd 
* spring runner
* piramida honeycomb icecone

## rest:
* idempotency
* status codes
* metody http
* biblioteki serializujace
* key rest principles
* projektowaniea api

## message queues - kafka:
* consumer group
* co jesli wiecej konsumerow niz partycji - instancje aplikacji lub watki listenera
* gwarancja kolejnosci
* header
* konfiguracja




--- 

## TODO:

### java
* design patterns
* streams
* optionals
* create exception and print stack trace
* threads, sync example and more stuff, ExecutorService = Executors.
* memory leak
* comparator vs comparable
* collections 
* equals and hash code
* heap
* gc


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
* circuit breaker


### db
* properties
* procedures
* partial index (where name != null)
* jooq
* querydsl
* transactions, what causes rollback, checked unchecked (also in try block)
* criteria api, criteria + meta model, query dsl, specification
* spring.datasource.hikari.auto-commit=false

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