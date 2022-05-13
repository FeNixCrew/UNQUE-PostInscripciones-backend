pipeline {
  agent any
  stages {
    stage('Run tests') {
      steps() {
        sh 'chmod +x gradlew'
        sh './gradlew -v'
      }
    }
  }
}
