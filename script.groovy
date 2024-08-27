def buildApp(){
    echo "Building Application ..."
}


def pushImage(){
    echo 'Building Image ...'
    sh "docker build -t 10.1.10.74:9006/dockerhosted-repo:${BUILD_NUMBER} ."

    echo 'Pushing image to docker hosted rerpository on Nexus'

    withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'PSW', usernameVariable: 'USER')]){
        sh "docker login 10.1.10.74:9006 -u ${USER} -p ${PSW}"
        sh "docker push 10.1.10.75:9006/dockerhosted-repo:${BUILD_NUMBER}"
    }
}

def testApp(){
    echo 'Testing Application ...'
}

def deployApp(){
    echo 'Deploying Application ...'
}

def commitChanges(){
    withCredentials([usernamePassword(credentialsId: 'github', passwordVariable: 'PSW', usernameVariable: 'USER')]) {
        sh 'git config --global user.name "jenkins"'
        sh 'git config --global user.email "jenkins@gmail.com"'
        sh "git remote set-url origin https://${USER}:${PSW}@github.com/JvIadEye/Jenkins-Nexus-CICD"

        //sh '''
        //    #!/bin/bash
        //    sed -i 's/Version:.*/Version: '"${BUILD_NUMBER}"'/g' index.html
        //'''
        
        sh 'git add .'
        sh 'git commit -m "updated version to ${BUILD_NUMBER}"'
        sh 'git push origin HEAD:main'
    }
    echo 'Changes committed by jenkins'
}

return this
