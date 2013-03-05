#Address Book
Project uses the following technology stack:
* Spring MVC
* Hibernate
* JBehave
* Selenium
* Gradle

It's not a finished product but is mainly used to indicate how I would go about developing software, so the UI is only sufficient to show functionality and the validation is only rudimentary in nature i.e. not allowing for emtpy data.  Also you can only add data, the funtionality to modify and remove data has not been implemented as I only wanted to spend the necessary time needed on the application, so only implemented what was necessary to meet the spec.  Also the application hasn't fully been javadocs, only the classes where I thought where appropriate.

#Project Structure
The project is made up of two projects:
* webapp
* features

##webapp
This is where the main work for the application resides.  It contains the applications code and unit tests.  The task to run the webapp is jettyRun.  For the weabpp to work you need to define an environment variable "-Denv=<environment>" eg. *gradle jettyRun -Denv=prod*.  Running the application without the environment variable will result in a exception occuring and the application not running.

The are 3 environments defined:

* dev

This uses an in memory database, so on restarting of the server all data is lost and the database is re-created.

* prod

This creates a database on the filesystem under the user directory named addressbook. i.e. ~/addressbook.  This location can be changed under the property file src\main\resources\app-prod.properties.  Change the end of the jdbc.url to the location you wish.

* test

This uses an in memory database.  This environment was envisioned to run the features project.  Features has not been fully developed, so left this as an in memory database, ideally would want a database under the filesystem that will allow us to run the tests in features.

##features
This is where the user stories for the application are defined.  There is only one user defined as speccing the whole application would have meant spending more time then necessary.  Again this is just to show how I would like to develop software, at the moment the user story is failing.

#Running application
The applicatoin uses gradle as it build script.  So you would first need to have gradle installed on the machine where you would want to run the application.  Gradle can be found at http://www.gradle.org/downloads and the installation guide at http://www.gradle.org/docs/current/userguide/installation.html.

Once gradle has been setup then you can run the application by following these steps:
* go the the webapp folder
* within the webapp folder run the following command:

```
gradle jettyRun -Denv=prod
```

* open a browser and go to the location http://localhost:8080/webapp
* now interact with the application

# Assumptions
* you can only compare between 2 address books
* address book can only be defined within the application, you can't import an address book from another location

#Useful gradle commands
* ```gradle check```

Validates the projects defined by running the test cases as well as running the checkstyle, findbugs, pmd plugins over the project. Note there are still checkstyle warnings as the project has been created to show how I would develop software, so time was a factor.  The reports for the pluings can be found under build/reports

* ```gradle test```

Runs the units test for the application.

* ```gradle eclipse```

Create the eclipse .project and .classpath

* ```gradle jettyRun```

Runs the webapp.

* ```gradle clean```

remove the build directory.
