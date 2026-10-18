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

Containerization with Docker

The application was containerized using Docker and Apache Tomcat. Containerization packages the application and its runtime environment together, allowing the application to run consistently across supported environments.

The project uses a Dockerfile based on an Apache Tomcat 10.1 image with Java 17. The generated WAR artifact is copied into Tomcat's web application directory so that Tomcat can deploy and serve the application when the container starts.

The container image used in the Kubernetes deployment was:

cen4802taskmanager:2.0

To view locally available Docker images, run:

docker images

Docker provides the container runtime, while Kubernetes manages the deployment and execution of the containerized application.

Kubernetes Deployment

Kubernetes was used to deploy and manage the containerized Business Task Manager application in a local Docker Desktop Kubernetes environment.

The deployment configuration is located in:

k8s/deployment.yaml

This file defines the Kubernetes Deployment, the application container image, the container port, and the desired number of replicas. The initial configuration requests one application replica.

The Kubernetes Service configuration is located in:

k8s/service.yaml

This file defines a NodePort Service named taskmanager-service. The Service selects application pods using the app: taskmanager label and forwards traffic to port 8080.

The Service configuration requests NodePort 30080. For the verified browser test, Kubernetes port forwarding was used to provide access through localhost port 8081.

Deploy the Application

From the project root directory, validate the Kubernetes configuration with:

kubectl apply --dry-run=client -f k8s/

To create the application Deployment and Service, run:

kubectl apply -f k8s/

Verify the Deployment

To inspect the Deployment, run:

kubectl get deployments

To inspect the application pods, run:

kubectl get pods -o wide

To inspect the Service, run:

kubectl get services

For more detailed information about the Deployment, run:

kubectl describe deployment taskmanager

These commands display the deployment status, replica counts, pod status, container image, network configuration, and other information about the application's runtime environment.

Access the Application

To access the deployed application through local port forwarding, run:

kubectl port-forward service/taskmanager-service 8081:8080

Keep the terminal running while accessing the application.

The verified API endpoint is:
http://localhost:8081/api/tasks

This endpoint was tested in a web browser and returned a response from the deployed Java application.

The root URL (/) returns a Tomcat 404 response because the application functionality is exposed through its API endpoint rather than a page at the root path.

Scale the Application

Kubernetes can adjust the number of application replicas without manually starting individual containers.

To scale the Deployment to two replicas, run:

kubectl scale deployment taskmanager --replicas=2

To verify the rollout, run:

kubectl rollout status deployment/taskmanager

To confirm the number and status of the replicas, run:

kubectl get deployments

kubectl get pods -o wide

During testing, the Deployment successfully reached two available replicas, with both application pods running and ready.

Remove the Deployment

To remove the application resources created from the Kubernetes configuration files, run:

kubectl delete -f k8s/

Verify that the application resources have been removed:

kubectl get deployments

kubectl get pods

kubectl get services

The Task Manager Deployment, its pods, and its Service should no longer appear. The default Kubernetes Service remains because it belongs to the cluster infrastructure.

This cleanup process removes the deployed application resources without uninstalling Kubernetes or deleting the local Docker image.

Automated Testing and Continuous Integration

JUnit 5 is used to test application functionality. Maven executes these tests as part of the build process and can also run them independently.

The project workflow uses Git and GitHub to manage source code changes and review updates through pull requests. GitHub Actions was also used in the earlier development workflow to automate the Maven build and test process.

Automated testing helps identify problems before changes are merged or packaged for deployment. Containerization and Kubernetes extend this workflow by providing a way to run, inspect, and manage the application in a deployment environment.

Technologies Used

Java 17: Application programming language.

Jakarta REST: REST API implementation.

Apache Tomcat 10.1: Java web application runtime.

Apache Maven: Build automation and dependency management.

JUnit 5: Automated unit testing.

Docker: Application containerization.

Kubernetes: Container deployment and management.

Git and GitHub: Source control and collaborative development.

GitHub Actions: Automated build and test workflow.

Deployment Notes

The Kubernetes configuration files are included in the k8s directory. A local Docker Desktop Kubernetes environment was used for testing, so a cloud provider or paid hosting service was not required.

The local container image must be available to the Kubernetes environment before deployment. If the application image changes, rebuild the application and Docker image as appropriate, update the image tag in the deployment configuration, and redeploy the updated configuration.

The application can be deployed, inspected, scaled, and removed using Kubernetes commands from the project root directory.