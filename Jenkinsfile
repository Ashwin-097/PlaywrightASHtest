pipeline 
{
    agent any
    
    stages 
    {
		
		          stage('Setup JDK 21 & Maven') {
            steps {
                bat '''
                if not exist tools mkdir tools
                cd tools

                rem === Download Maven if not already present ===
                if not exist apache-maven-3.9.16 (
                  curl -L -o maven.zip https://downloads.apache.org/maven/maven-3/3.9.16/binaries/apache-maven-3.9.16-bin.zip
                  powershell -command "Expand-Archive maven.zip ."
                  rem Flatten folder name
                  for /d %%i in (apache-maven-3.9.16*) do (
                    if not "%%i"=="apache-maven-3.9.16" (
                      move "%%i" apache-maven-3.9.16
                    )
                  )
                )

rem === Download JDK 21 if not already present ===
if not exist jdk-21 (
  curl -L -o jdk.zip https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.11+10/OpenJDK21U-jdk_x64_windows_hotspot_21.0.11_10.zip
  powershell -command "Expand-Archive jdk.zip ."
  rem Flatten folder name
  for /d %%i in (jdk-21*) do (
    if not "%%i"=="jdk-21" (
      move "%%i" jdk-21
    )
  )
)

 
            

                rem === Set environment variables for this build ===
                set JAVA_HOME=%WORKSPACE%\\tools\\jdk-21
                set PATH=%JAVA_HOME%\\bin;%WORKSPACE%\\tools\\apache-maven-3.9.16\\bin;%PATH%

                echo ===== JAVA VERSION =====
                "%WORKSPACE%\\tools\\jdk-21\\bin\\java.exe" -version

                echo ===== MAVEN VERSION =====
                "%WORKSPACE%\\tools\\apache-maven-3.9.16\\bin\\mvn.cmd" -v
                '''
            }
        }
        
        stage('Build') 
        {
            steps
            {
                 git branch: 'origin', url: 'https://github.com/ashwinjxxx/PlaywrightASHtest.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
                
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/naveenanimation20/Playwright-Java-PageObjectModel'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regressions.xml"
                    
                }
            }
        }
        
        
        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'build', 
                                  reportFiles: 'TestExecutionReport.html', 
                                  reportName: 'HTML Extent Report', 
                                  reportTitles: ''])
            }
        }
        
        
        
        
    }
}