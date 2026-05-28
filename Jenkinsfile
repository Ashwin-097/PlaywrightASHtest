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


        stage('Setup JDK 21 & Maven') {
            steps {
                bat '''
                if not exist tools mkdir tools
                cd tools

                rem === Download Maven if not already present ===
                if not exist apache-maven-3.9.6 (
                  curl -L -o maven.zip https://downloads.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip
                  powershell -command "Expand-Archive maven.zip apache-maven-3.9.6"
                )

                rem === Download JDK 21 if not already present ===
                if not exist jdk-21 (
                  curl -L -o jdk.zip https://download.java.net/java/GA/jdk21/latest/jdk-21_windows-x64_bin.zip
                  powershell -command "Expand-Archive jdk.zip jdk-21"
                )

                rem === Set environment variables for this build ===
                set JAVA_HOME=%WORKSPACE%\\tools\\jdk-21
                set PATH=%JAVA_HOME%\\bin;%WORKSPACE%\\tools\\apache-maven-3.9.6\\bin;%PATH%

                echo ===== JAVA VERSION =====
                "%WORKSPACE%\\tools\\jdk-21\\bin\\java.exe" -version

                echo ===== MAVEN VERSION =====
                "%WORKSPACE%\\tools\\apache-maven-3.9.6\\bin\\mvn.cmd" -v
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