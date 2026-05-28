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
                    git branch: 'origin', url: 'https://github.com/ashwinjxxx/PlaywrightASHtest.git'
                }
            }
        }

        stage('Build App') {
            steps {
                dir('app') {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Run Tests') {
            steps {
                dir('tests') {
                    // Run Maven tests, which will trigger your TestRunner.java
                    sh 'mvn clean test'
                }
            }
        }

        stage('Deploy if Tests Pass') {
            steps {
                dir('app') {
                    sh 'echo "Deploying Hello World app..."'
                    // Replace with actual deploy command
                }
            }
        }
    }

    post {
        always {
            // Publish JUnit test results
            junit 'tests/target/surefire-reports/*.xml'

            // Archive Cucumber HTML report if generated
            archiveArtifacts artifacts: 'tests/target/cucumber-report.html', onlyIfSuccessful: true
        }
    }
}