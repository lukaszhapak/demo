# DEMO

Demo application

--- 

TODO:

* mysql createDatabaseIfNotExist=true, maybe the same exists for postgre
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
* reflection
* logs to elk https://www.youtube.com/watch?v=hvYUwUmHB6M
* metrics to elk
* actuator
* design patterns
* db indexes, partial index (where name != null)
* test container with kafka
* group modules, spring core, web, database, tools
* more comments with explanation
* wiremock / test server
* @Query with update (@Modifying)
* Awaitility tests for async executions
* mockito extension  (@Mock, @Captor)
* spock tests
* custom handler interceptor
* arch unit
* java streams
* clean test data: @sql with delete script  @before each with repo.delete(), @transactional on test class
* test jpa repository, extend jpa repository work on the same entity
* Domain Specific Language for test scenarios, like givenUserIsSaved()   whenUserNameIsUpdated(String name)    thenUser
* mock application context to get prototype scope bean in unit test
* @Scheduled tests, override test cron to run every second, or disable cron and manually start bean method   cron="-" means 'DISABLE'
* mongo: embedded, docker