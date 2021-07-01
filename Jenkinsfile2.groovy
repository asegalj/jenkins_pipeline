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
        

        stage('Test') {
            steps {
                echo "Testing release ${RELEASE}..."
                 echo "executing python script"
                           sh """\
                    python
                    import json
                    with open('result.json', 'w') as f:
                            json.dump('{"created by": "python script"}', f)
                    """.stripIndent()
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
        always {
            echo 'Prints whether deploy happend or not, success of failure'
              sh "git checkout -b Dev"
                  sh "git add result.json"
                  sh "git commit -m 'add result.json file to Dev Stage'"
                  sh "git checkout -b Staging"          
                  //  git commit -m "add result.json file to ${env.RELEASE}" 
                  sh "git push https://github.com/asegalj/jenkins-git.git Dev Staging"
        }
    }
}
