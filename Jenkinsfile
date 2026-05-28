pipeline {
    agent {
     
    }

    stages {
        stage('Checkout Tests Repo') {
            steps {
                git branch: 'origin',
                    url: 'https://github.com/ashwinjxxx/PlaywrightASHtest'
            }
        }

        stage('Install Dependencies') {
            steps {
                bat 'npm install'
            }
        }

        stage('Run Playwright Tests') {
            steps {
                bat 'npx playwright test'
            }
        }

        stage('Checkout App Repo') {
            steps {
                script {
                    // Only run if tests passed
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
            
        