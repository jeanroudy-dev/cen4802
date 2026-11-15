Business Task Manager

Author: Jean Roudy Alexis

Description

The Business Task Manager is a Java web application designed to help businesses manage and track tasks. Users can view tasks, descriptions, and statuses, and mark pending tasks as completed.

The application uses Java 17, Jakarta REST, Apache Tomcat, Maven, JUnit 5, Git, and GitHub.

Build and Run

The project uses Apache Maven as its automated build tool.

To build the application and execute unit tests from the command line, run:

mvn clean package

To execute automated tests without packaging the application, run:


mvn test

A successful build generates the following WAR artifact in the target directory:


target/cen4802taskmanager-1.0-SNAPSHOT.war

The WAR file can be deployed to Apache Tomcat and accessed through a web browser.

The application endpoint is:

http://localhost:8080/cen4802taskmanager/api/tasks

The application can be rebuilt after source code changes using the same Maven command:

mvn clean package

The newly generated WAR file can then be deployed to Tomcat to run the updated version of the application.

Project Structure

The project follows the standard Maven directory structure, separating application source code from automated unit tests:

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
test/
java/
com/example/cen4802taskmanager/
pom.xml
mvnw
mvnw.cmd

The pom.xml file contains the Maven project configuration, Java version, build settings, and JUnit 5 dependencies configured under the test scope.

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

Infrastructure as Code (Terraform)

The project uses Terraform to implement Infrastructure as Code (IaC) and manage Docker resources.

The Terraform configuration provisions the following resources:

- A Docker application container running the Business Task Manager.
- A dedicated Docker network.
- A persistent Docker volume for Tomcat logs.
- A container memory limit of 768 MB.

The application container is accessible through:

http://localhost:8081/api/tasks

Terraform commands used to manage the infrastructure include:

terraform init
terraform validate
terraform plan
terraform apply
terraform destroy

The application was tested successfully, and Terraform was used to modify the container memory allocation and verify the change. After testing, Terraform successfully destroyed the managed resources.

The existing Docker application image was retained locally.