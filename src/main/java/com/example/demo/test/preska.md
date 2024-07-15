# TEST

## Unit test

### implementation test
* architecture diagram
* test code
* mock and objects initialization are blocking refactoring
* some kind of string generator, mock requires that this class and that method returns that value, requires that given class is using this generator

### behavior test
* architecture diagram, black box
* facade design pattern
* test code
* manual initialization of tested classes, lots of not needed code in tests, that touches implementation
* configuration class giving build module, most of the classes are not spring beans, two examples
* refactoring, extracting services, keep the api stable don't break the contract and refactor whatever you want in the inside, private methods just for one public method
* base test with created instances
* in memory implementation can hold some complex logic based on some value like id
* works like a mock

### prepare test data
* shared list instance
* shared object instance
* trait in spock, or field of abstract class in junit, interface does not work because of static
* lombok chained setter
* reflection test utils 
* personas, name give us info

### unit test tools
* 3 types of test methods: return value or exception, interaction with something, or state change
* argument captor and argument matcher, diagram
* assertJ recursive comparison
* helper method with assertions, refactoring model
* stubbing with callback
* it can be easier with in memory implementation

### in memory implementation
* test code, in memory repository implementation
* reflection and abstract entity example
* easier than mocking?
* hentai example, repo in production code, not touched in tests, tests base on facade only, black box is bigger, diagram, config creates instance of in memory repo
* not only repo but also message queue, with methods to get list of published messages, or some complex processing based on some property like ID
* example with unique value in db
* shared instance so the db or message queue will get erased automatically
* method in repo to populate data, maybe i constructor, can be reused later in data insert runner
* log in constructor
* having this repository make those tests more similar to integration test, reusing tests

### refactoring
* test coverage don't go too high on that
* mutations manually or pit
* pitest, config in pom
* target classes target tests, mutators groups or single mutator list 
* pit report
* don't chase 100% usually not worth it
* can be connected to pull request

### failing assertions
* we know that test are working
* we can see error messages
* error messages in junit assertJ and spock

### TDD
* implementation vs behavior
* diagram
* red, read documentation understand requirements, write tests
* green, implement the code to get the test pass
* refactor the code and run tests
* in behavior test red is design of api, interface, facade and refactor is design of internal classes
* in implementations tests refactor allows to extract methods inside of a class

### archunit
* layered
* hexagonal


## Integration test
* diagram
* ice cone vs honeycomb
* testing everything as integration tests, only happy path or only flows that bring money
* later we will talk about reusing tests

### insert test data
* manual insert with method call, http request or queue message,   can hide more complex inserts in helper methods
* sql scripts, table refactor requires a lot of changes in scripts
* flyway migrations, create table, add test data then refactor table add column or something
* command line runner inserting given java objects, we have pointer to those values in db but not to the exact record, can reuse code that populates in memory repository in unit tests

### clean test data
* in unit tests repo will be cleaned for each test
* repo delete all in before all or in after all, in a while we will talk about repo overriding
* in test container we can set reuse for database, so we can see the tables after the tests
* jdbc template to clean all tables
* sql script to clean all tables
* jdbc get all tables from db so no need to refactor clean script
* selective repo clean, only some methods do some mess in db
* transactional, transaction propagation in code or in some frameworks, transaction is attached to given thread, so in case of tomcat, message queue or scheduled tasks there will be no rollback

### overriding repository
* new repo for tests with helper methods delete all, find all, count or anything
* if we use JpaRepository we have that all but if we use Repository we need to declare methods on our own

### logging sql
* show sql
* log sql
* p6spy, file with properties, can be modified to print ready sql queries

### quick perf
* expect select
* expect insert
* have sql annotations and jvm annotations

### search
* insert one, test to get one and test to get zero
* insert two, get one verify data
* insert multiple, verify that all found fit in criteria, in case of sql or flyway insert there will be a lot of records
* insert multiple once, and do all tests in one method
* parameterized tests

### http server
* mock mvc
* rest assured 
* rest template
 
### http client
* wiremock with method stub, wiremock can randomize port and assign it into property
* wiremock with resource stub
* mock server
* restito

### kafka
* embedded kafka 
* listener test, kafka template and some kind of assertion
* polling conditions, in base class to make less mess
* awaitility from test containers in junit
* publisher test, reminder about outbox pattern, 2 phase commit and saga choreography
* test listener and config to get messages

### cron
* override cron to execute every second and wait for job to happen
* override cron to disable job and manually execute it
* assertions on state changes

### test containers
* jdbc tc in url
* dynamic property source
* reuse container
* container have to be static to not restart context
* toxi proxy
* service connection from spring boot 3.1

### context restart
* base class with mocks or configurations

### dsl
* hiding implementation details in one place, url, status code and other stuff
* methods for assertions as mentioned
* complex methods like login or payment operations, we can set in db by script that something is already logged or paid 
* tests are much easier to read

### slices
* temporary test for time of development to check some db method or http mapping
* starts faster since it not loads whole context
* but whole context should be started to test whole app
* any ideas how to use it?

### reusing tests
* base class with test methods
* unit and integration test extending base and overriding operation methods
* verify event was sent

### others
* one assertion per test or fit multiple asserts on one operation to save time
* one assertion show exactly what is not working but we can use verify all
