pipeline {
    agent any

    environment {
        // [수정필요] 생성될 도커 이미지 이름
        IMAGE_NAME = "getz"
        // [수정필요] 도커 컨테이너 이름 (docker-compose에 등록할 이름)
        CONTAINER_NAME = "getz-backend"
        // [수정필요] 사용할 포트 (Traefik과 연결된 내부 포트)
        APP_PORT = "8080"
    }

    stages {
        stage('Checkout') {
            steps {
                // 깃허브에서 코드 가져오기
                git branch: 'feat/getz-11',
                    credentialsId: 'github-token',
                    url: 'https://github.com/andeok/getz.git'
            }
        }

        stage('Build Gradle') {
            steps {
                dir('backend') {
                    sh 'chmod +x gradlew'
                    sh './gradlew clean build -x test'
                }
            }
        }

        stage('Docker Build') {
            steps {
                // Dockerfile을 이용해 이미지 빌드
                sh "docker build -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // 기존 컨테이너가 돌고 있다면 멈추고 삭제 (에러 무시)
                    try {
                        sh "docker stop ${CONTAINER_NAME}"
                        sh "docker rm ${CONTAINER_NAME}"
                    } catch (Exception e) {
                        echo "기존 컨테이너가 없어서 삭제 과정을 건너뜁니다."
                    }

                    // 새로운 컨테이너 실행
                    // Traefik 라벨을 붙여서 실행 (docker-compose 없이 단독 실행 시)
                    sh """
                        docker run -d \
                        --name ${CONTAINER_NAME} \
                        --network web-net \
                        --restart unless-stopped \
                        -l "traefik.enable=true" \
                        -l "traefik.http.routers.spring.rule=Host(`getz.kr`) && PathPrefix(`/api`)" \
                        -l "traefik.http.routers.spring.entrypoints=websecure" \
                        -l "traefik.http.routers.spring.tls.certresolver=myresolver" \
                        ${IMAGE_NAME}:latest
                    """
                }
            }
        }
    }

    post {
        success {
            echo '배포 성공! 🎉'
        }
        failure {
            echo '배포 실패... 로그를 확인하세요. 😭 '
        }
    }
}