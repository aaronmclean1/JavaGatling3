pipeline {
    agent any

    tools {
        maven 'maven' // Match the name configured in Jenkins
        jdk 'jdk'        // Match your JDK version
    }

    stages {
        stage('Checkout') {
            steps {
                //git 'https://github.com/aaronmclean1/JavaGatling3.git'
                git branch: 'main', url: 'https://github.com/aaronmclean1/JavaGatling3.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean gatling:test'
                //sh 'gatling:test -Dgatling.simulationClass=GatlingRun'
            }
        }

        stage('Simulations') {
            steps {
                sh 'pwd'
                //sh '/opt/homebrew/opt/bzt/bin/bzt BlazeTest.yml'
            }
        }

        stage('Publish Reports') {
            steps {
                archiveArtifacts artifacts: 'target/gatling/**/*', allowEmptyArchive: true
            }
        }
    }
}
