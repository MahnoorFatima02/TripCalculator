pipeline {
    agent any
    environment {
        MAVEN_HOME = '/opt/homebrew/Cellar/maven/3.9.9/libexec'
        PATH = "/opt/homebrew/bin:${MAVEN_HOME}/bin:${env.PATH}"
        DOCKERHUB_CREDENTIALS_ID = 'Docker_hub'
        DOCKERHUB_REPO = 'tripcalculator'
        DOCKER_IMAGE_TAG = 'latest_v1'
        DOCKERHUB_USER = 'mahnoor95' // Define the Docker Hub user
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
                        script {
                            docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                        }


            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    // Use Docker registry credentials to push images
//                     def commitHash = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
//                     def imageTag = "${DOCKERHUB_USER}/${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}"
//                     def commitTag = "${DOCKERHUB_USER}/${DOCKERHUB_REPO}:${commitHash}"

                    withCredentials([usernamePassword(credentialsId: DOCKERHUB_CREDENTIALS_ID, usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        // Log in to Docker Hub
                        sh "/usr/local/bin/docker login -u ${DOCKERHUB_USER} -p ${DOCKERHUB_PASSWORD}"

                        // Construct the full image tag
                        def imageTag = "${DOCKERHUB_USER}/${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}"
                        // Push Docker image to Docker Hub
                        sh "/usr/local/bin/docker push ${imageTag}"
                        // Push Docker images to Docker Hub
//                         sh "/usr/local/bin/docker push ${DOCKER_IMAGE_TAG}"
//                         sh "/usr/local/bin/docker push ${commitTag}"
                    }
                }
            }
        }
    }
}
