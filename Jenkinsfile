pipeline {
    agent any

    stages {
        stage('Checkout Tests Repo') {
            steps {
                git branch: 'origin',
                    url: 'https://github.com/ashwinjxxx/PlaywrightASHtest'
            }
        }

        stage('Build & Run Tests') {
            steps {
                // Run Maven build and Cucumber tests
                bat 'mvn clean test'
            }
        }
        stage('Count Scenarios') {
            steps {
                // Parse Cucumber JSON report and echo counts
                bat '''
                powershell -Command ^
                  "$report = Get-Content target/cucumber-report.json | ConvertFrom-Json; ^
                   $scenarios = ($report | ForEach-Object { $_.elements }).Count; ^
                   $steps = ($report | ForEach-Object { $_.elements | ForEach-Object { $_.steps } }).Count; ^
                   Write-Host ('Total scenarios executed: ' + $scenarios); ^
                   Write-Host ('Total steps executed: ' + $steps)"
                '''
            }
        }

        stage('Checkout App Repo') {
            steps {
                script {
                    if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {
                        git branch: 'master',
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