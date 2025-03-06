pipeline {
    agent any
     environment {
            MAVEN_HOME = '/opt/homebrew/Cellar/maven/3.9.9/libexec'
//              PATH = "${MAVEN_HOME}/bin:${env.PATH}"
            PATH = "/opt/homebrew/bin:${MAVEN_HOME}/bin:${env.PATH}"
             DOCKERHUB_CREDENTIALS_ID = 'dockerhub-credentials'
             DOCKERHUB_REPO = 'mahnoor95/tripcalculator'
             DOCKER_IMAGE_TAG = 'latest'
        }
    stages {
        stage('Checkout') {
            steps {
                git 'git@github.com:MahnoorFatima02/TripCalculator.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }

         stage('Build Docker Image') {
                    steps {
                        // Build Docker image
                        script {
                            docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                        }
                    }
                }
                stage('Push Docker Image to Docker Hub') {
                    steps {
                        // Push Docker image to Docker Hub
                        script {
                            docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                                docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                            }
                        }
                    }
                }
    }
}
