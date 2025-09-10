INSERT INTO users (email, password, nickname, user_type, created_at, modified_at, deleted)
VALUES ('test1@test.com',
        '8z3hGJPQ6Z6sx5E+10Qq0GuRwDjPI/13CmPaGF9MysEBXUhRGnW1HIdGLIOz1+MNLqwwCnBtnsPE0efZ08BI0g==:b/1USgOylkQ69rOVXtbdKlzxIgiF5u5guqsMItk7b8fFNmUtIXVdX/Qdg8w/gly6TfFe46VsmZJUnCw4WOTnUg==',
        'testuser-1', 'USER', NOW(), NOW(), false);

INSERT INTO users (email, password, nickname, user_type, created_at, modified_at, deleted)
VALUES ('test2@test.com',
        '8z3hGJPQ6Z6sx5E+10Qq0GuRwDjPI/13CmPaGF9MysEBXUhRGnW1HIdGLIOz1+MNLqwwCnBtnsPE0efZ08BI0g==:b/1USgOylkQ69rOVXtbdKlzxIgiF5u5guqsMItk7b8fFNmUtIXVdX/Qdg8w/gly6TfFe46VsmZJUnCw4WOTnUg==',
        'testuser-2', 'USER', NOW(), NOW(), false);

INSERT INTO users (email, password, nickname, user_type, created_at, modified_at, deleted)
VALUES ('test3@test.com',
        '8z3hGJPQ6Z6sx5E+10Qq0GuRwDjPI/13CmPaGF9MysEBXUhRGnW1HIdGLIOz1+MNLqwwCnBtnsPE0efZ08BI0g==:b/1USgOylkQ69rOVXtbdKlzxIgiF5u5guqsMItk7b8fFNmUtIXVdX/Qdg8w/gly6TfFe46VsmZJUnCw4WOTnUg==',
        'testuser-3', 'USER', NOW(), NOW(), false);


insert into product (title, description, status, seller_id, created_at, modified_at, deleted)
values ('아이폰 13 프로(액정파손)', '액정파손되었지만 잘 사용했습니다.', 'AVAILABLE', 1, now(), now(), false);

insert into product (title, description, status, seller_id, created_at, modified_at, deleted)
values ('캐논 eos r8 카메라', '보증 1년남음. 5000컷', 'AVAILABLE', 1, now(), now(), false);

insert into product (title, description, status, seller_id, created_at, modified_at, deleted)
values ('캐논 35.8 팔아요', '보증 25년 10월까지', 'AVAILABLE', 1, now(), now(), false);

insert into product (title, description, status, seller_id, created_at, modified_at, deleted)
values ('갤럭시 s25 울트라.', '액정파손되었지만 잘 사용했습니다.', 'AVAILABLE', 2, now(), now(), false);

insert into product (title, description, status, seller_id, created_at, modified_at, deleted)
values ('그림 작품 팝니다.', '유명한 화가의 그림이에요.', 'AVAILABLE', 3, now(), now(), false);


INSERT INTO auction (title, description, start_price, end_price, current_price, start_time, end_time, bid_increment, status, product_id, created_at, modified_at, deleted)
VALUES ('[수리용/부품용] 아이폰 13 프로', '액정은 파손되었으나 터치 및 모든 기능 정상 작동합니다. 저렴하게 시작합니다.', 150000, 400000, 150000, '2025-09-09 18:00:00', '2025-09-16 22:00:00', 10000, 'ONGOING', 1, NOW(), NOW(), false);

INSERT INTO auction (title, description, start_price, end_price, current_price, start_time, end_time, bid_increment, status, product_id, created_at, modified_at, deleted)
VALUES ('[A급] 캐논 EOS R8 미러리스 카메라', '실사용 적은(5000컷 미만) 카메라입니다. 보증 기간 1년 남아있어 안심하고 구매하세요.', 1200000, 1800000, 1200000, '2025-09-12 20:00:00', '2025-09-19 22:00:00', 50000, 'SCHEDULED', 2, NOW(), NOW(), false);

INSERT INTO auction (title, description, start_price, end_price, current_price, start_time, end_time, bid_increment, status, product_id, created_at, modified_at, deleted)
VALUES ('캐논 RF 35mm F1.8 단렌즈', '보증 기간이 25년 10월까지 넉넉하게 남았습니다. 상태 최상입니다.', 350000, 550000, 350000, '2025-09-10 21:00:00', '2025-09-15 21:00:00', 10000, 'SCHEDULED', 3, NOW(), NOW(), false);

INSERT INTO auction (title, description, start_price, end_price, current_price, start_time, end_time, bid_increment, status, product_id, created_at, modified_at, deleted)
VALUES ('갤럭시 S25 울트라 (액정 파손)', '화면 일부 파손되었으나 사용에 지장 없습니다. 최신 플래그십 모델을 저렴하게 경험해보세요.', 250000, 600000, 250000, '2025-09-09 10:00:00', '2025-09-16 23:00:00', 10000, 'ONGOING', 4, NOW(), NOW(), false);

INSERT INTO auction (title, description, start_price, end_price, current_price, start_time, end_time, bid_increment, status, product_id, created_at, modified_at, deleted)
VALUES ('유명 화가의 유화 작품', '거실 인테리어에 어울리는 멋진 작품입니다. 진품 보증서 포함입니다.', 2500000, 8000000, 2500000, '2025-09-10 10:00:00', '2025-09-20 22:00:00', 100000, 'ONGOING', 5, NOW(), NOW(), false);

