# Jenkins-Nexus-CICD-DEMO

Sample Java app prints Hello World message in log console. Source stored in GITHUB srinivish/my-java-app repo build through maven deployed to Docker container Created pipeline using Jenkins

here is the set of commands

git clone https://github.com/srinivish/my-java-app.git cd my-java-app/ mvn clean install -DskipTests docker build -t java-app . docker run --name java-container -d java-app docker logs java-container <should be able to view Hello World! message in log console> Typical Java command to run jar or application CMD ["java", "-jar", "my-java-app.jar"]

Install TRIVY for docker image scan Trivy is a simple and comprehensive vulnerability scanner for containers and other artifacts, suitable for CI.

sudo apt-get install wget apt-transport-https gnupg lsb-release wget -qO - https://aquasecurity.github.io/trivy-repo/deb/public.key | sudo apt-key add - echo deb https://aquasecurity.github.io/trivy-repo/deb $(lsb_release -sc) main | sudo tee -a /etc/apt/sources.list.d/trivy.list sudo apt-get update sudo apt-get install trivy
