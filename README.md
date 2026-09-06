Business Task Manager

Author: Jean Roudy

Description

The Business Task Manager is a Java web application designed to help businesses manage and track tasks. Users can view tasks, descriptions, and statuses, and mark pending tasks as completed.

The application uses Java 17, Jakarta REST, Apache Tomcat, Maven, Git, and GitHub.

Build and Run

The project uses Apache Maven as its automated build tool.

To build the application from the command line, run:

mvn clean package

A successful build generates the following WAR artifact in the target directory:

target/cen4802taskmanager-1.0-SNAPSHOT.war

The WAR file can be deployed to Apache Tomcat and accessed through a web browser.

The application endpoint is:

http://localhost:8080/cen4802taskmanager/api/tasks

The application can be rebuilt after source code changes using the same Maven command:

mvn clean package

The newly generated WAR file can then be deployed to Tomcat to run the updated version of the application.

Project Structure

The project follows the standard Maven directory structure:

src/
main/
java/
com/example/cen4802taskmanager/
HelloApplication.java
HelloResource.java
Task.java
    resources/
        META-INF/
            beans.xml
pom.xml
mvnw
mvnw.cmd

The pom.xml file contains the Maven project configuration, dependencies, Java version, and build settings.

Version Control

The project is managed with Git using a feature-branch workflow and GitHub pull requests.

The development process includes creating feature branches, making changes, committing the changes, pushing branches to GitHub, creating pull requests, reviewing changes, and merging approved changes into the main branch.

Future Improvements

The project can be expanded with features such as:

Adding new tasks
Editing tasks
Deleting tasks
Assigning tasks to employees
Filtering tasks
Database storage
User authentication
Improved task management and reporting
