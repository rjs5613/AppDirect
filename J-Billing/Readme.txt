Building the Project:
	1. Goto Root Directory and run command : mvn clean install
	2. To Skip Test Cases add -DskipTests in command,e.g: mvn clean install -DskipTests
	
Running Only Test Cases
	1. To run all test cases run the command : mvn surefire:test
	2. To Run Specific Test Class : mvn surefire:test -Dtest=<Test Class Name> eg: mvn surefire:test -Dtest=ProductServiceTest
	3. To Run Specific Test Case : mvn surefire:test -Dtest=<Test Clas>#<Test Method>
		e.g: mvn surefire:test -Dtest=ProductServiceTest#testAggregateSortedPrices

DB Setup:
	1. Database used is PostgreSQL with properties specified in src/main/resources/application.properties
	2. DB Initializing will be done by flyway.
	3. Script present at src/main/resources/db/migration/postgres
	4. For Running Test cases H2 in memory database is used with properties specified in src/test/resources/application.properties
	5. Script present at src/test/resources/db/migration/h2

Running the Application :
	1. Goto Root Directory and run command : mvn spring-boot:run or
	2. Goto RootDirectory/target and run command : java -jar J-Billing-0.0.1-SNAPSHOT.jar

Ideal Price Calculator JOB:
	1. Job is currently scheduled to run every 30seconds.
	2. Scheduling can be enabled and disabled using property : idealprice.enablejob
	3. Job Runs in batches, Batch size can be specified using property : idealprice.batchsize
	4. Currently only one implementation of Ideal Calculator is present but if we have more than one implementation we can control the strategy based using

Rest APIS
	Products->
		1. Create : POST localhost:8080/products
			body : {"name" : "Iphone 5s","description" : "Apple Product Product","basePrice" : "100"}
		2. Get Price Info : GET localhost:8080/products/1/prices

	Stores->
		1. Create : POST localhost:8080/stores 
			body: {"name" : "Lexicon Store","description" : "Priority Store"}
		2. Price of productin store : POST localhost:8080/stores/products/ 
			body : {"store" : 1,"product" : 1,"price": 110}  --> This API signature should be changed to "stores/{storeId}/products"

	Jobs->
		1. Start : POST localhost:8080/jobs/pricecalculator?command=start

		
		
		
		
		