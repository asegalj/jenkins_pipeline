pipeline {
    agent any
    environment {
        RELEASE='20.04'
    }
    stages {
        stage('Build') {
            agent any
            environment {
                LOG_LEVEL='INFO'
            }
            steps {
                echo "Building release ${RELEASE} with log level ${LOG_LEVEL}..."
            }
        }
        

      
        stage('Deploy') {
            input {
                message 'Deploy?'
                ok 'Do it!'
                parameters {
                    string(name: 'TARGET_ENVIRONMENT', defaultValue: 'PROD', description: 'Target deployment environment')
                }
            }
            steps {
                echo "Deploying release ${RELEASE} to environment ${TARGET_ENVIRONMENT}"
            }
        }
    }
    post{
        success {
                 
                  sh "git checkout -b Dev | git checkout Dev"
                  sh 'echo "test" > result.json'
                  sh "ls"
                  sh "git add result.json"
                  sh "ls"
                  sh "git commit -m 'add result.json file to Dev Stage'"
                  sh "git checkout -b Staging | git checkout Staging" 
                sh "git config --global user.email 'devopstasks2021@gmail.com'"
                  sh  "git config --global user.name 'devops2021devops'"
                  sh "git config --global user.password 'Devops2021'"
                  //  git commit -m "add result.json file to ${env.RELEASE}" 
                  sh "git push https://github.com/asegalj/jenkins-git.git Dev Staging"
        }
    }
}
