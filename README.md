# ⚡ Getz (겟츠)


![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-Distributed%20Lock-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-Messaging-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)
![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-D24939?style=for-the-badge&logo=jenkins&logoColor=white)

---

[//]: # ()
[//]: # (## 📖 프로젝트 소개 &#40;Introduction&#41;)

[//]: # ()
[//]: # (**Getz**는 단순한 경매 사이트가 아닙니다. **구매자의 심리적 경쟁**과 **서버의 기술적 한계**를 동시에 도전하는 프로젝트입니다.)

[//]: # ()
[//]: # (일반적인 입찰 방식&#40;가격 상승&#41;과 시간이 지날수록 가격이 떨어지는 타임어택 방식&#40;가격 하락&#41;이 **하나의 상품 위에서 동시에 진행**되는 **'Moving Ceiling&#40;움직이는 천장&#41;'** 알고리즘을 구현했습니다.)

[//]: # ()
[//]: # (이 과정에서 발생하는 **대규모 트래픽**과 **재고 동시성 문제&#40;Concurrency Issue&#41;**를 해결하기 위해 견고한 백엔드 아키텍처를 설계했습니다.)

[//]: # ()
[//]: # (### 🎯 핵심 목표)

[//]: # (1.  **무결성 보장:** 수천 명의 사용자가 동시에 '즉시 구매'를 눌러도 단 1명만 성공해야 한다 &#40;Redis Lock&#41;.)

[//]: # (2.  **실시간성:** 가격 변동과 낙찰 정보는 0.5초 이내에 모든 사용자에게 전파되어야 한다 &#40;WebSocket&#41;.)

[//]: # (3.  **확장성:** 알림, 로그 저장 등의 부가 기능은 메인 로직에 영향을 주지 않아야 한다 &#40;RabbitMQ&#41;.)

[//]: # ()
[//]: # (---)

[//]: # ()
[//]: # (## 🏗️ 시스템 아키텍처 &#40;Architecture&#41;)

[//]: # ()
[//]: # (*&#40;여기에 나중에 아키텍처 다이어그램 이미지를 넣으세요. ex: /docs/images/architecture.png&#41;*)

[//]: # ()
[//]: # (### 🛠️ Tech Stack)

[//]: # (| Category | Technology | Description |)

[//]: # (| :-- | :-- | :-- |)

[//]: # (| **Language** | Java 21 | 최신 LTS 버전의 Java 활용 |)

[//]: # (| **Framework** | Spring Boot 3.x | 빠른 개발과 안정적인 생태계 |)

[//]: # (| **Database** | MySQL 8.0 | 경매 데이터, 사용자, 주문 정보의 영속성 보장 |)

[//]: # (| **Cache & Lock** | **Redis** | 선착순 구매 동시성 제어&#40;Distributed Lock&#41; 및 조회 성능 최적화 |)

[//]: # (| **Message Queue** | **RabbitMQ** | 주문 성공/실패 이벤트 비동기 처리 및 서비스 간 결합도 감소 |)

[//]: # (| **Storage** | OCI Object Storage | 상품 이미지의 효율적인 저장 및 관리 |)

[//]: # (| **DevOps** | Docker, Jenkins | CI/CD 파이프라인 구축 및 자동 배포 |)

[//]: # ()
[//]: # (---)

[//]: # ()
[//]: # (## 💡 주요 기능 &#40;Key Features&#41;)

[//]: # ()
[//]: # (### 1. 하이브리드 경매 &#40;Hybrid Auction Engine&#41;)

[//]: # (* **상향식&#40;English&#41;:** 사용자가 호가를 부르면 현재가가 올라갑니다.)

[//]: # (* **하향식&#40;Dutch&#41;:** 일정 시간마다 즉시 구매가가 자동으로 떨어집니다.)

[//]: # (* **The Clash:** [입찰가]와 [즉시 구매가]가 만나는 순간, 자동으로 최고 입찰자에게 낙찰됩니다.)

[//]: # ()
[//]: # (### 2. 동시성 제어 &#40;Concurrency Control&#41;)

[//]: # (* Redisson을 활용한 **분산 락&#40;Distributed Lock&#41;** 적용.)

[//]: # (* 재고 차감 및 낙찰 처리 시 **Race Condition** 완벽 방어.)

[//]: # (* JMeter를 이용한 부하 테스트 진행 &#40;예정&#41;.)

[//]: # ()
[//]: # (### 3. 이벤트 기반 알림 &#40;Event-Driven Notification&#41;)

[//]: # (* 경매 종료, 낙찰 성공, 상위 입찰자 등장 등 주요 이벤트를 **RabbitMQ**로 발행.)

[//]: # (* 이메일/SMS 발송 로직을 비동기로 분리하여 메인 트랜잭션 응답 속도 향상.)

[//]: # ()
[//]: # (### 4. 재경매 시스템 &#40;Re-Auction&#41;)

[//]: # (* 유찰된 상품을 기존 데이터&#40;조회수, 찜 등&#41;를 유지한 채, 회차&#40;Retry Count&#41;만 늘려 재등록하는 프로세스 구현.)

[//]: # ()
[//]: # (---)

[//]: # ()
[//]: # (## 💾 데이터베이스 모델링 &#40;ERD&#41;)

[//]: # ()
[//]: # (*&#40;여기에 나중에 ERD 이미지를 넣으세요. ex: /docs/images/erd.png&#41;*)

[//]: # ()
[//]: # (* **Users:** 사용자 정보)

[//]: # (* **Products:** 상품 원천 데이터 &#40;OCI 이미지 URL 포함&#41;)

[//]: # (* **Auctions:** 경매 진행 정보 &#40;하이브리드 규칙 포함&#41;)

[//]: # (* **Bids:** 입찰 내역 &#40;Log 성격&#41;)

[//]: # (* **Orders:** 최종 주문 내역)

[//]: # ()
[//]: # (---)

[//]: # ()
[//]: # (## 🚀 시작하기 &#40;Getting Started&#41;)

[//]: # ()
[//]: # (### Prerequisites)

[//]: # (* Docker & Docker Compose)

[//]: # (* Java 21 &#40;JDK&#41;)

[//]: # ()
[//]: # (### Installation)

[//]: # (1. **Clone the repository**)

[//]: # (   ```bash)

[//]: # (   git clone [https://github.com/your-username/getz.git]&#40;https://github.com/your-username/getz.git&#41;)

[//]: # (   cd getz)