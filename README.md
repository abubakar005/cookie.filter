# Cookie Filter

## Setup Project

Pre-requisites
1. Java 11
2. Maven (Build Tool)
3. Tool to open the project like Intellij, Eclipse , etc

## Build Application JAR
1. Open the project in any tool like IntelliJ and configure java 11
2. Build the project using any of the following commands

~~~
mvn clean package
~~~

~~~
mvn clean install
~~~

Or by using the IntelliJ Idea UI Lifecycle (clean, install)

Executable JAR can also be created using the Maven tool installed on the local machine and running the any of the above mentioned commands from the project root directory.


## Run Application JAR
- Once the JAR file created, run the following command to parse the log file and get the most active cookie(s) against the required date

- Run the following command from the project root directory

~~~
java -jar target\cookie.filter-0.0.1-SNAPSHOT.jar -f csv-file-path -d selected-date
~~~

- Example:

~~~
java -jar target\cookie.filter-0.0.1-SNAPSHOT.jar -f  src\main\resources\cookie_log.csv -d 2018-12-08
~~~

# Tech Stack

1. Java 11
2. Spring Boot
3. Maven (Build Tool)
4. CommandLine interface
5. CSV file reader
