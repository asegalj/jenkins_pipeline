pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/asegalj/jenkins_pipeline.git', branch: 'main'
            }
        }

    stage('Build') {
        steps {
            sh 'echo hello world'
        }

        post {
            always {
                sh 'echo test'
            }
        }
    }
  }
}