pipeline {
    agent any
    tools {
        maven 'Maven 3.8.1'
    }
    environment {
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=false"
    }
    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/AtharvaAP17/student-management-api.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
