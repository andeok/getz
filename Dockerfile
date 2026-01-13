# 1. 베이스 이미지 설정 (Java 21 실행 환경, ARM64 자동 지원)
FROM eclipse-temurin:21-jre-jammy

# 2. 작업 디렉토리 생성
WORKDIR /app

# 3. (중요) Jenkins가 빌드한 JAR 파일을 컨테이너 안으로 복사
# build/libs/ 폴더 안에 있는 .jar 파일을 app.jar라는 이름으로 복사합니다.
# 주의: libs 폴더 안에 실행 가능한 jar 파일이 하나만 있어야 합니다.
ARG JAR_FILE=backend/build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 4. 포트 노출 알림 (문서화 목적, 실제 연결은 docker command에서 함)
EXPOSE 8080

# 5. 컨테이너 실행 시 수행할 명령어 (스프링 부트 실행)
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app/app.jar"]