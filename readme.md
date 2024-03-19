# DEMO

Demo application

--- 

### TODO:

* entity manager
* jdbc spring / non spring
* hibernate with no spring
* sequences
* procedures
* test entity manager
* rest template
* open feign
* property source
* spring expression language
* conditionals on..
* n plus one
* logs to elk https://www.youtube.com/watch?v=hvYUwUmHB6M
* metrics to elk
* actuator
* design patterns
* db indexes, partial index (where name != null)
* more comments with explanation
* @Query with update (@Modifying)
* custom handler interceptor
* arch unit
* java streams
* clean test data: @sql with delete script  @before each with repo.delete(), @transactional on test class
* test jpa repository, extend jpa repository work on the same entity
* Domain Specific Language for test scenarios, like givenUserIsSaved()   whenUserNameIsUpdated(String name)    thenUser
* mock application context to get prototype scope bean in unit test
* @Scheduled tests, override test cron to run every second, or disable cron and manually start bean method   cron="-" means 'DISABLE'
* @WithMockUser, whe performing http call and when calling a bean method
* mongodb, embedded for tests
* email client
* criteria api, criteria + meta model, query dsl, specification
* jpa lock
* jpa dynamic update
* jpa dynamic insert
* jpa get reference id
* fetch join
* entity graph
* JoinColumn vs mapped by
* quick perf: @ExpectSelect(1)
* jpa projection return record
* jpa dynamic projections take class as argument and return it
* jooq
* querydsl



 @Value("${prop}") private final String prop; works with the @RequiredArgsConstructor
lombok.copyableAnnotations+=org.springframework.beans.factory.annotation.Value


@AutoConfigureWebTestClient.
webClient
.get().uri("/")
.exchange()
.expectStatus().isOk()
.expectBody(String.class).isEqualTo("Hello World");


@SpringBootApplication
@ConfigurationPropertiesScan
public class TestConfig {
}

@SpringBootApplication
@EnableConfigurationProperties(CustomConfigPojo.class)
public class TestConfig {
}


73.6 Configure Access Logging
Access logs can be configured for Tomcat and Undertow via their respective namespaces.

For instance, the following logs access on Tomcat with a custom pattern.

server.tomcat.basedir=my-tomcat
server.tomcat.accesslog.enabled=true
server.tomcat.accesslog.pattern=%t %a "%r" %s (%D ms)
