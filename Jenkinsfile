pipeline {
    agent any

    stages {
        stage('Checkout Tests Repo') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/ashwinjxxx/PlaywrightASHtest'
            }
        }

        stage('Install Dependencies') {
            steps {
                // Install Cucumber + Playwright globally
                bat '''
                    npm install -g @cucumber/cucumber
                    npm install -g playwright
                '''
            }
        }

        stage('Run Cucumber + Playwright Tests') {
            steps {
                // Point to your actual feature + step definition paths
                bat '''
                    cucumber-js ^
                      --require src/test/java/steps/**/*.js ^
                      --require src/test/java/runner/**/*.js ^
                      src/test/resources/features/**/*.feature
                '''
            }
        }

        stage('Checkout App Repo') {
            steps {
                script {
                    if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {
                        git branch: 'main',
                            url: 'https://github.com/octocat/Hello-World'
                    } else {
                        echo "Skipping app checkout because tests failed."
                    }
                }
            }
        }

        stage('Deploy App') {
            steps {
                script {
                    if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {
                        bat 'echo Deploying application...'
                        bat 'dir'
                    } else {
                        echo "Skipping deployment because tests failed."
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo '✅ Tests passed, app deployed.'
        }
        failure {
            echo '❌ Tests failed, deployment skipped.'
        }
    }
}