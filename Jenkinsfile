pipeline {
    agent {
        // Run on a Windows node
        label 'windows'
    }

    stages {
        stage('Checkout Tests Repo') {
            steps {
                // Clone your Playwright test repo
                git branch: 'origin',
                    url: 'https://github.com/ashwinjxxx/PlaywrightASHtest'
            }
        }

        stage('Install Dependencies') {
            steps {
                // Install Node.js dependencies
                bat 'npm install'
            }
        }

        stage('Run Playwright Tests') {
            steps {
                // Run Playwright tests
                bat 'npx playwright test'
            }
        }

        stage('Checkout App Repo') {
            when {
                // Only run if tests passed
                succeeded()
            }
            steps {
                // Clone a sample app repo for deployment
                git branch: 'main',
                    url: 'https://github.com/octocat/Hello-World'
            }
        }

        stage('Deploy App') {
            when {
                succeeded()
            }
            steps {
                // Example deployment step (replace with real deployment commands)
                bat 'echo Deploying application...'
                bat 'dir'  // Just listing files here as placeholder
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