pipeline {
    agent {
        label {
            label ""
          customWorkspace "custom_workspace_${BUILD_NUMBER}"
        }
    }
    stages {
        stage("foo") {
            steps {
                echo "Workspace dir is ${pwd()}"
            }
        }
    }
}
