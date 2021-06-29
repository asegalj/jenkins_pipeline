pipeline {
    agent any

    stages {
        // stage('Checkout') {
        //     steps {
        //         git url: 'https://github.com/asegalj/jenkins_pipeline.git', branch: 'main'
        //     }
        // }

    stage('Build') {
        steps {
            echo "This is build number $BUILD_NUMBER of demo $DEMO"
            sh '''
                echo "Using a multi-line shell step"
                chmod +x test.sh
                ./test.sh
              '''
            sh 'echo hello world'
        }
    }

    stage('Test') {
        steps {
            sh 'echo testing'
        }
    }
  }
}
