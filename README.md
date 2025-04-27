# Student Management API - End-to-End CI/CD Pipeline

##  Project Overview

This project demonstrates the complete automation of building, testing, analyzing, Dockerizing, and deploying a Java Spring Boot microservice (**Student Management API**) using a CI/CD pipeline.

All stages are automated using open-source tools like Jenkins, Maven, SonarQube, Docker, and Ansible.

---

##  Tech Stack

- **Java 17** + **Spring Boot 3**
- **Maven** (Build and Dependency Management)
- **JUnit 5**, **Mockito**, **JaCoCo** (Testing and Code Coverage)
- **Jenkins** (CI Server)
- **SonarQube** (Static Code Analysis - hosted on AWS EC2)
- **Docker** (Containerization)
- **Ansible** (Automated Deployment)
- **AWS EC2** (Cloud Hosting)
- **GitHub** (Source Control)

---

##  Project Structure

| Layer | Description |
|:------|:------------|
| Controller | Handles API HTTP Requests |
| Service | Contains Business Logic |
| Repository | Interfaces with In-Memory H2 Database |
| Model | Defines the Student Entity |

---

##  CI/CD Pipeline Stages

1. **Source Code Management**
   - GitHub Repository for code versioning
   - GitHub webhook (via Ngrok) triggers Jenkins build

2. **Build and Test**
   - Maven compiles the Java code
   - JUnit5 and Mockito execute Unit Tests
   - JaCoCo generates Test Coverage reports

3. **Static Code Analysis**
   - SonarQube analyzes the codebase for:
     - Bugs
     - Vulnerabilities
     - Code Smells
   - Quality Gates enforced (Grades: A on Reliability, Security, Maintainability)

4. **Dockerization**
   - A Dockerfile builds an image for the Student Management API
   - Docker images encapsulate application environment

5. **Automated Deployment**
   - Ansible pulls the latest Docker image
   - Ansible stops old containers and deploys the new version
   - Ensures fast, consistent deployment to local server

---

##  Results

| Metric | Result |
|:-------|:-------|
| Code Build | Successful |
| Unit Tests | Passed |
| Static Analysis | SonarQube Grades: A |
| Test Coverage | ~34% Overall (100% on Service Layer) |
| Docker Image Build | Successful |
| Deployment | Successful using Ansible |

CI/CD Pipeline runs end-to-end successfully with every push to GitHub.

---

##  Testing and Quality Assurance

- **Unit Tests**:
  - Full test coverage for `StudentService`.
  - Partial coverage for `Student` entity.
  - No controller tests (future improvement).

- **Code Coverage**:
  - ~34% Overall
  - 100% Line and Branch Coverage for Service Layer
  
- **SonarQube Analysis**:
  - Reliability: A
  - Security: A
  - Maintainability: A
  - Code Duplications: 0.0%

---

## 🛠️ Deployment Architecture


GitHub (Push) → Jenkins (Build + Test + SonarQube Analysis) → Docker Image Build → Ansible → Deployment

- Local Jenkins triggers the CI pipeline.
- SonarQube server hosted on AWS EC2.
- Application deployed inside a Docker container on local machine.

---

##  Challenges Faced and Future Improvements

| Challenge | Plan for Improvement |
|:---------|:----------------------|
| Low Controller Test Coverage | Add integration tests using MockMvc |
| Deployment Only Local | Extend deployment to Kubernetes (K8s) |
| Docker Image Versioning | Implement version tagging |
| Security | Add Authentication Layer (JWT) for APIs |

---

##  Key Takeaways

- Built an end-to-end CI/CD system for a cloud-native Java microservice.
- Automated build, testing, code quality analysis, containerization, and deployment.
- Integrated industry-standard tools (Jenkins, SonarQube, Docker, Ansible) into one professional workflow.
- Gained hands-on experience running SonarQube on cloud (AWS EC2).

---

##  Acknowledgements

- MSc. in Software Design (Cloud Native Computing) - TUS
- Jenkins and SonarQube Official Documentation
- Spring Boot Community

---

##  Author

**Atharva Pailwan**  
A00325723 | MSc. Cloud Native Computing (2024)

---
