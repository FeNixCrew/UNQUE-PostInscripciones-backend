pipeline {
  agent { label 'pruebasCI' }
  stages {
    stage('Run tests') {
      steps() {
        withGradle() {
          sh './gradlew -v'
        }
      }
    }
  }
}
