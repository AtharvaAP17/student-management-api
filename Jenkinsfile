pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9' // Match this exactly to what's installed in Jenkins
    }

    environment {
        SONARQUBE_ENV = 'SonarQubeEC2'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/AtharvaAP17/student-management-api.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${env.SONARQUBE_ENV}") {
                    sh 'mvn sonar:sonar'
                }
            }
        }


        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
