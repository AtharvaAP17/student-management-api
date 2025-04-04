pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9' // Match this exactly to what's installed in Jenkins
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

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
