# TEST

## Unit test

### basics

* junit assert vs assertJ
* assertJ recursive comparison
* mockito, argument matcher, argument captor, stubbing with callback, mockito extension @Mock
* prepare test data, lombok chain setter, reflection test utils, shared object instance interface
* tests don't share object instances - list example


### refactoring
* tests vs refactoring
* black box or white box, testing behavior or implementation
* bugs in white box tests
* refactoring with white box and a lot of mocks
* repository in memory for unit tests vs mocking all repository call and setting verifies
* hentai example
* refactoring - coverage and mutants
* pit framework mutation testing
* archunit


## Integration test

* testing ice cone, pyramid, honeycomb
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
* example of mockmvc, rest assured and rest template, webclient headers params body, authentication

### search
* jpa specification
* criteria api
* rentea example 
* testing: put 2 search by name
* testing: put 1, one test gets it other not
* parameterized test here

### dsl / test framework
* basic example with easily readable format methods
* methods like register product hiding url, headers, body, status and other stuff - copy methods from unit tests to integration tests

### context restart
* abstract test, with common config
* @mockbean refreshes mock
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
* integration: code in other jar that is already tested, like outbox or kafka publisher
* unit: other classes, modules,  external adapters

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

### security
* with mock user
* principal in unit test

### testing external libraries / modules
* assume that they work properly
* or verify that we configured and used them properly
* multiple actions triggered by the same event example

### tdd bdd
* some may say bdd is tdd done right

### test slices
* data jpa
* web mvc



test implementation instead of mock, like repo, exception for given param, or kafka impl with methods like get all messages

reusing unit tests, extend class and test methods will be executed, or abstract class with test scenarios

assertJ  isEqualToIgnoringNewLines

live templates for tests