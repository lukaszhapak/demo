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
* mongodb
* email client
* criteria api, criteria + meta model, query dsl, specification
* jpa lock


# TEST

## Unit test

* junit assert vs assertJ
* tests vs architecture
* black box or white box, testing behavior / contract or implementation, tests not compiling (lot of mocks)
* tests don't share object instances - list example
* assertJ recursive comparison
* refactoring - coverage and mutants
* pit framework mutation testing
* hentai example 
* test ice cream, pyramid, honeycomb
* repository in memory for unit tests vs mocking all repository call and setting verifies
* dry - wet
* testing everything as integration tests, only happy path or only flows that bring money
* test speed up
* reflection test utils, method with map as argument and update given field
* test jpa repository, extend jpa repository work on the same entity

## Integration test

### data clean up
* @Transactional, transaction propagation, threads, embedded tomcat in different thread
* @BeforeEach repo.delete()
* sql script 

### wiremock
* @Autoconfigure annotation with random port
* port in properties
* method stub and resources stub

### kafka
* embedded kafka vs container
* listener test
* sender test

### cron
* overriding cron '-' disable and manually call method
* overriding cron '0/3 sec' and use awaitility

### http clients
* webEnvironment mock vs random port
* example of mockmvc, rest assured and rest template, headers params body, authentication

### search
* jpa specification
* criteria api
* rentea example 
* testing: put 2 search by name
* testing: put 1, one test gets it other not
* parameterized test here

### test data
* sql script inserting data, need fix when tables are changing
* test migrations with data
* manually inserting data with method calls
* test methods for more complex inserts

### dsl / test framework
* basic example with easily readable format methods

### context restart
* abstract test, with common config
* @mockbean
* @dirties context
* properties
* different type web environment
* different bean
* different context
* annotations with data slices

### test containers
* embedded database vs container
* testing migrations 
* testing native queries

### mocks
* code in other jar that is already tested, like outbox or kafka publisher

### speed up tests
* put some stuff in other jar and test it there
* -ea -noverify -mx2048m -Xverify:none -XX:TieredStopAtLevel=1