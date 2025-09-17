INSERT INTO users (email, password, nickname, user_type, created_at, modified_at)
VALUES ('test1@test.com',
        '8z3hGJPQ6Z6sx5E+10Qq0GuRwDjPI/13CmPaGF9MysEBXUhRGnW1HIdGLIOz1+MNLqwwCnBtnsPE0efZ08BI0g==:b/1USgOylkQ69rOVXtbdKlzxIgiF5u5guqsMItk7b8fFNmUtIXVdX/Qdg8w/gly6TfFe46VsmZJUnCw4WOTnUg==',
        'testuser-1', 'USER', NOW(), NOW());

INSERT INTO users (email, password, nickname, user_type, created_at, modified_at)
VALUES ('test2@test.com',
        '8z3hGJPQ6Z6sx5E+10Qq0GuRwDjPI/13CmPaGF9MysEBXUhRGnW1HIdGLIOz1+MNLqwwCnBtnsPE0efZ08BI0g==:b/1USgOylkQ69rOVXtbdKlzxIgiF5u5guqsMItk7b8fFNmUtIXVdX/Qdg8w/gly6TfFe46VsmZJUnCw4WOTnUg==',
        'testuser-2', 'USER', NOW(), NOW());

INSERT INTO users (email, password, nickname, user_type, created_at, modified_at)
VALUES ('test3@test.com',
        '8z3hGJPQ6Z6sx5E+10Qq0GuRwDjPI/13CmPaGF9MysEBXUhRGnW1HIdGLIOz1+MNLqwwCnBtnsPE0efZ08BI0g==:b/1USgOylkQ69rOVXtbdKlzxIgiF5u5guqsMItk7b8fFNmUtIXVdX/Qdg8w/gly6TfFe46VsmZJUnCw4WOTnUg==',
        'testuser-3', 'USER', NOW(), NOW());


insert into product (title, description, status, seller_id, created_at, modified_at)
values ('아이폰 13 프로(액정파손)', '액정파손되었지만 잘 사용했습니다.', 'AVAILABLE', 1, now(), now());

insert into product (title, description, status, seller_id, created_at, modified_at)
values ('캐논 eos r8 카메라', '보증 1년남음. 5000컷', 'AVAILABLE', 1, now(), now());

insert into product (title, description, status, seller_id, created_at, modified_at)
values ('캐논 35.8 팔아요', '보증 25년 10월까지', 'AVAILABLE', 1, now(), now());

insert into product (title, description, status, seller_id, created_at, modified_at)
values ('갤럭시 s25 울트라.', '액정파손되었지만 잘 사용했습니다.', 'AVAILABLE', 2, now(), now());

insert into product (title, description, status, seller_id, created_at, modified_at)
values ('그림 작품 팝니다.', '유명한 화가의 그림이에요.', 'AVAILABLE', 3, now(), now());


INSERT INTO auction (product_id, winner_id, status, start_price, current_price, final_price, buy_now_price,
                     bid_increment, bid_count, start_at, end_at)
VALUES (1, -- product_id
        NULL, -- winner_id
        'OPEN', -- status
        150000, -- start_price
        150000, -- current_price (시작가와 동일)
        NULL, -- final_price
        400000, -- buy_now_price
        10000, -- bid_increment
        0, -- bid_count
        '2025-09-14 18:00:00', -- start_at
        '2025-09-18 22:00:00' -- end_at
       );

-- 2. 시작 전 경매 (내일 저녁 시작)
INSERT INTO auction (product_id, winner_id, status, start_price, current_price, final_price, buy_now_price,
                     bid_increment, bid_count, start_at, end_at)
VALUES (2, -- product_id
        NULL, -- winner_id
        'READY', -- status
        1200000, -- start_price
        1200000, -- current_price
        NULL, -- final_price
        1800000, -- buy_now_price
        50000, -- bid_increment
        0, -- bid_count
        '2025-09-16 20:00:00', -- start_at
        '2025-09-23 22:00:00' -- end_at
       );

-- 3. 현재 진행중인 경매 (방금 시작, 5일 후 마감)
INSERT INTO auction (product_id, winner_id, status, start_price, current_price, final_price, buy_now_price,
                     bid_increment, bid_count, start_at, end_at)
VALUES (3, -- product_id
        NULL, -- winner_id
        'OPEN', -- status
        350000, -- start_price
        350000, -- current_price
        NULL, -- final_price
        NULL, -- buy_now_price (즉시구매 없음)
        10000, -- bid_increment
        0, -- bid_count
        '2025-09-15 17:00:00', -- start_at
        '2025-09-20 21:00:00' -- end_at
       );

-- 4. 현재 진행중인 경매 (장기 경매)
INSERT INTO auction (product_id, winner_id, status, start_price, current_price, final_price, buy_now_price,
                     bid_increment, bid_count, start_at, end_at)
VALUES (4, -- product_id
        NULL, -- winner_id
        'OPEN', -- status
        250000, -- start_price
        250000, -- current_price
        NULL, -- final_price
        600000, -- buy_now_price
        10000, -- bid_increment
        0, -- bid_count
        '2025-09-10 10:00:00', -- start_at
        '2025-09-20 23:00:00' -- end_at
       );

-- 5. 시작 전 경매 (주말 시작)
INSERT INTO auction (product_id, winner_id, status, start_price, current_price, final_price, buy_now_price,
                     bid_increment, bid_count, start_at, end_at)
VALUES (5, -- product_id
        NULL, -- winner_id
        'READY', -- status
        2500000, -- start_price
        2500000, -- current_price
        NULL, -- final_price
        8000000, -- buy_now_price
        100000, -- bid_increment
        0, -- bid_count
        '2025-09-20 10:00:00', -- start_at
        '2025-09-30 22:00:00' -- end_at
       );

insert into product_image (product_id, image_url)
values (1,
        'https://images.unsplash.com/photo-1592750475338-74b7b21085ab?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxpcGhvbmV8ZW58MXx8fHwxNzU3NDM1Mjc0fDA&ixlib=rb-4.1.0&q=80&w=1080');

insert into product_image (product_id, image_url)
values (2,
        'https://images.unsplash.com/photo-1583056971486-b6167117fe27?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx2aW50YWdlJTIwY2FtZXJhJTIwYXVjdGlvbnxlbnwxfHx8fDE3NTc0MDc3NDR8MA&ixlib=rb-4.1.0&q=80&w=1080');

insert into product_image (product_id, image_url)
values (3,
        'https://images.unsplash.com/photo-1680810897186-372717262131?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxsdXh1cnklMjB3YXRjaCUyMGF1Y3Rpb258ZW58MXx8fHwxNzU3MzU5MDc4fDA&ixlib=rb-4.1.0&q=80&w=1080');

insert into product_image (product_id, image_url)
values (4,
        'https://images.unsplash.com/photo-1584917865442-de89df76afd3?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxsdXh1cnklMjBiYWd8ZW58MXx8fHwxNzU3NDM1MjU0fDA&ixlib=rb-4.1.0&q=80&w=1080');

insert into product_image (product_id, image_url)
values (5,
        'https://images.unsplash.com/photo-1693405102972-a15e3e540c66?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhbnRpcXVlJTIwZnVybml0dXJlJTIwYXVjdGlvbnxlbnwxfHx8fDE3NTc0MDc3NTN8MA&ixlib=rb-4.1.0&q=80&w=1080');