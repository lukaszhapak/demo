# TEST

## Unit test

### basics

* junit assert vs assertJ
* tests don't share object instances - list example
* assertJ recursive comparison
* dry - wet
* test speed up
* nested tests with before each
* @mockbean refreshes
* created timestamp, updated timestamp in unit tests
* methods like register product hiding url, headers, body and other stuff - copy methods from unit tests to integration tests

### design
* tests vs architecture
* black box or white box, testing behavior / contract or implementation, tests not compiling (lot of mocks)
* refactoring - coverage and mutants
* pit framework mutation testing
* hentai example
* test ice cream, pyramid, honeycomb
* repository in memory for unit tests vs mocking all repository call and setting verifies


## Integration test

* testing everything as integration tests, only happy path or only flows that bring money

### clean test data
* @BeforeEach repo.deleteAll()
* sql script
* do not clean at all, watch out for unique constraints, choose assertions that will not fail
* after or before test method
* test jpa repository, extend jpa repository work on the same entity 
* @Transactional, transaction propagation, threads, embedded tomcat in different thread, scheduled or kafka example
* clean data in unit test (repository in memory)
* get table names via script

### add test data
* sql script inserting data, need fix when tables are changing
* test migrations with data
* manually inserting data with method calls, http requests, jdbc template, test entity manager
* test methods for more complex inserts

### prepare test data
* reflection test utils method with map as argument and update given field
* lombok.accessors.chain 
* shared instance of dto, just use setter

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

### time
* inject fixed clock
* truncate millis
* mock localDateTime.now()

### logging sql
* show sql
* log sql
* p6spy