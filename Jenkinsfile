pipeline {
    agent any
    stages {
        stage('Clean'){
            steps{
                sh 'mvn clean'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Test'){
            steps {
                sh 'mvn test -Dmaven.test.failure.ignore=true'
            }
        }
        stage('PMD'){
            steps {
                sh 'mvn pmd:pmd'
            }
        }
        stage('JaCoCo'){
            steps {
                sh 'mvn jacoco:report'
            }
        }
        stage('Javadoc'){
            steps {
                sh 'mvn javadoc:javadoc'
            }
        }
        stage('Site'){
            steps {
                sh 'mvn site'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: '**/target/site/**/*.*', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.jar', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.war', fingerprint: true
            junit '**/target/surefire-reports/*.xml'
        }
    }
}def runMaven(String args) {
    if (isUnix()) {
        sh "mvn ${args}"
    } else {
        bat "mvn ${args}"
    }
}

pipeline {
    agent any
    stages {
        stage('Build and Test') {
            steps {
                script {
                    runMaven('clean verify -Dmaven.test.failure.ignore=true')
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: '**/target/site/**/*.*', allowEmptyArchive: true, fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.jar', allowEmptyArchive: true, fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.war', allowEmptyArchive: true, fingerprint: true
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
        }
    }
}