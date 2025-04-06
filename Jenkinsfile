pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'Maven 3.9.9'
        SONARQUBE_SERVER = 'SonarQube EC2'
        DOCKER_IMAGE = 'atharvapailwan7/student-api-cicd'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/AtharvaAP17/student-management-api.git'

            }
        }

        stage('Build with Maven') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn clean package"
            }
        }

        stage('Run Tests') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn test"
            }
        }

        stage('Code Quality - SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube'){
                    sh "${MAVEN_HOME}/bin/mvn sonar:sonar"
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push ${DOCKER_IMAGE}
                    """
                }
            }
        }

        stage('Deploy via Ansible') {
            steps {
                dir('ansible-deploy') {
                    sh """
                        source ../venv/bin/activate
                        ansible-playbook -i inventory deploy.yml --ask-become-pass
                    """
                }
            }
        }
    }

    post {
        always {
            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
            }
        }
        success {
            echo "Pipeline executed successfully!"
        }
        failure {
            echo "Pipeline failed!"
        }
}
