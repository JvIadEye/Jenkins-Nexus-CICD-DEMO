def gv_script
pipeline{
    agent any
    stages{
        stage('Init'){
            steps{
                script{
                    gv_script = load "script.groovy"
                }
            }
        }

        stage('Yum Install'){
            steps{
                script{
                    try {
                        gv_script.yumInstallApp()
                    } catch (Exception exception) {
                        echo "Package is blocked by IQ-Server. Please take a look at IQ-Server policy for more informations"
                    } 
                }
            }
        }
                    
        stage('Build'){
            steps{
                script{
                    gv_script.buildApp()
                }
            }
        }

        stage('Nexus Lifecycle Analysis') {
            steps{
                echo "Nexus Lifecycle Analysis in running ..."
    
                nexusPolicyEvaluation advancedProperties: '', enableDebugLogging: false, failBuildOnNetworkError: false, iqApplication: selectedApplication('sandbox-application'), iqInstanceId: 'Netpo_Demo', iqOrganization: '', iqStage: 'build', jobCredentialsId: ''
            }
        }

        stage('Test'){
            steps{
                script{
                    gv_script.testApp()
                }
            }
        }
        
        stage('Push image'){
            steps{
                script{
                    gv_script.pushImage()
                }
            }
        }

        stage('Deploy'){
            steps{
                script{
                    gv_script.deployApp()
                }
            }
        }
    }
    post{
        success{
            sh 'echo "Pipeline created successfully!"'
        }
    }
}
