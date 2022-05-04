pipeline {
  agent any
  stages {
    stage('Run tests') {
      steps() {
        withGradle() {
          sh './gradlew build'
        }
      }
    }
  }
}
