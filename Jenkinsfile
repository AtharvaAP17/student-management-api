pipeline {
    agent any

    triggers {
        githubPush()
    }

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
                withSonarQubeEnv('SonarQube') {
                    withCredentials([string(credentialsId: 'sonarqube-token', variable: 'SONAR_TOKEN')]) {
                        script {
                            def mvnHome = tool 'Maven 3.9.9'
                            withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                                sh '''
                                    mvn sonar:sonar \
                                    -Dsonar.projectKey=com.example:student-api \
                                    -Dsonar.host.url=http://107.21.51.131:9000 \
                                    -Dsonar.login=$SONAR_TOKEN
                                '''
                            }
                        }
                    }
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
                withCredentials([string(credentialsId: 'BECOME_PASS', variable: 'BECOME_PASS')]) {
                    dir('ansible-deploy') {
                        sh '''
                            ansible-playbook -i localhost, -c local deploy.yml --become --extra-vars "ansible_become_password=$BECOME_PASS"
                        '''
                    }
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
}
