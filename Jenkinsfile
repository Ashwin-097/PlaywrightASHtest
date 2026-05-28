pipeline {
    agent any

    stages {
        stage('Checkout App Code') {
            steps {
                dir('app') {
                    git branch: 'master', url: 'https://github.com/octocat/Hello-World.git'
                }
            }
        }

        stage('Checkout Test Scripts') {
            steps {
                dir('tests') {
                    git branch: 'test', url: 'https://github.com/ashwinjxxx/PlaywrightASHtest.git'
                }
            }
        }

        stage('Build App') {
            steps {
                dir('app') {
                    sh 'echo "Building Hello World app..."'
                }
            }
        }

        stage('Run Tests') {
            steps {
                dir('tests') {
                    sh './run-tests.sh ../app'
                }
            }
        }

        stage('Deploy if Tests Pass') {
            steps {
                dir('app') {
                    sh 'echo "Deploying Hello World app..."'
                }
            }
        }
    }
}