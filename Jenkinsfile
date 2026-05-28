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


        stage('Setup JDK & Maven') {
            steps {
                // Download and unpack Maven (example: 3.9.6)
                bat '''
                if not exist tools mkdir tools
                cd tools

                if not exist apache-maven-3.9.6 (
                  curl -L -o maven.zip https://downloads.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip
                  powershell -command "Expand-Archive maven.zip apache-maven-3.9.6"
                )

                setx MAVEN_HOME "%WORKSPACE%\\tools\\apache-maven-3.9.6"
                setx PATH "%WORKSPACE%\\tools\\apache-maven-3.9.6\\bin;%PATH%"
                '''
            }
        }


        stage('Build App') {
            steps {
                dir('app') {
                      bat '"%WORKSPACE%\\tools\\apache-maven-3.9.6\\bin\\mvn.cmd" clean package'
                }
            }
        }

        stage('Run Tests') {
            steps {
                dir('tests') {
                    // Run Maven tests, which will trigger your TestRunner.java
                     bat '"%WORKSPACE%\\tools\\apache-maven-3.9.6\\bin\\mvn.cmd" clean test'
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