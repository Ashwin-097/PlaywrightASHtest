pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'dummy', url: 'https://github.com/ashwinjxxx/PlaywrightASHtest.git'
            }
        }

        stage('Build') {
            steps {
                sh 'echo "Building project..."'
            }
        }

        stage('Test') {
            steps {
                sh 'echo "Running tests..."'
            }
        }

        stage('Deploy') {
            steps {
                sh 'echo "Deploying project..."'
            }
        }
    }
}