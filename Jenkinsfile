pipeline {
    agent any
    
    tools {
        jdk 'JDK_17'
        maven 'Maven_3.9.6'
    }
    
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
                    bat 'mvn clean package'
                }
            }
        }

        stage('Run Tests') {
            steps {
                dir('tests') {
                    // Run Maven tests, which will trigger your TestRunner.java
                    bat 'mvn clean test'
                }
            }
        }

        stage('Deploy if Tests Pass') {
            steps {
                    bat 'echo "Deploying Hello World app..."'
                    // Replace with actual deploy command
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