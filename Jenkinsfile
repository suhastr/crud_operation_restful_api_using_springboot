pipeline {
    agent any

    environment {
        // Update these variables with your actual information
        DOCKER_USERNAME = 'dunkdock'
        DOCKER_IMAGE = 'dunkdock/springbootcrudoperation' // Replace with your Docker image name
        DOCKER_TAG = 'latest' // Using the latest tag or you can use ${env.BUILD_ID} for unique tagging
        DOCKERFILE_PATH = 'Dockerfile' // Path to Dockerfile in your GitHub repository
        DEPLOYMENT_YAML_PATH = 'deployment.yaml' // Path to your Kubernetes deployment file in the repo
        SERVICE_YAML_PATH = 'service.yaml' // Path to your Kubernetes service file in the repo
        DOCKER_CREDENTIALS_ID = 'docker_cred' // Make sure this matches the ID in Jenkins credentials

    }

    stages {
        stage('Clone Repository') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }

        stage('Push Docker Image') {
    steps {
        script {
            // Get Docker Hub credentials
            withCredentials([string(credentialsId: 'docker_cred', variable: 'DOCKER_CREDENTIALS')]) {
                // Login to Docker Hub
                sh "echo \$DOCKER_CREDENTIALS | docker login --username ${DOCKER_USERNAME} --password-stdin"

                // Push Docker image to Docker Hub
                docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
            }
        }
    }
}
        stage('Run Docker Container') {
    steps {
        script {
            sh 'docker run -p 8081:8080 \
              -e "SPRING_DATASOURCE_URL=jdbc:postgresql://studentdb.cbsw4u0s2wwg.us-east-1.rds.amazonaws.com:5432/studentdb" \
              -e "SPRING_DATASOURCE_USERNAME=postgres" \
              -e "SPRING_DATASOURCE_PASSWORD=postgres" \
              -d dunkdock/dummy_demo:latest'
        }
    }
}


        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Deploy the Kubernetes deployment and service
                    sh "kubectl apply -f ${DEPLOYMENT_YAML_PATH}"
                    sh "kubectl apply -f ${SERVICE_YAML_PATH}"
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
