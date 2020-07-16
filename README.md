# jPetStore-SerenityBDD

##How to Run JpetStore on local

*To run JpetStore on local get the github url from https://github.com/mybatis/jpetstore-6
git clone git@github.com:mybatis/jpetstore-6.git

*To build the war file
./mvnw clean package --Mac
mvn clean package -- Windows (check .war file is present in the target folder)

*Start Tomcat and Run application
(Ensure you are on the root folder before running below command)
./mvnw cargo:run -P tomcat90 - Mac
mvnw cargo:run -P tomcar90 -- Windows
Navigate to http://localhost:8080/jpetstore/

##How to Run the test

*Edit Configuration>>Create a Maven configuration>>under VMs
>clean verify serenity:aggregate

*Environment specific run
>clean verify serenity:aggregate -Denvironment=dev

*Run a specific cucumber tag
>clean verify serenity:aggregate -Denvironment=dev "-Dcucumber.options=--tags @registration --tags ~@manual src/test/resources/features"

*Run on saucelabs
>clean verify serenity:aggregate -Denvironment=prod -Dsaucelabs.url=http://<username>:<access-key>@ondemand.saucelabs.com:80/wd/hub -Dsaucelabs.access.key=<access-key> -Dsaucelabs.user.id=<username>

##How to Configure
Check the serenity.conf and serenity.properties files
serenity.conf --> Has browser paths and environment urls
serenity.properties --> Has browser specified, timeouts, default url

