-- schema.sql: 최종 ERD 상세 정의 반영 (MySQL Syntax)

-- 1. location (지역) - (store가 참조하므로 먼저 생성)
CREATE TABLE location (
                          location_id BIGINT NOT NULL,
                          name VARCHAR(255) NOT NULL,
                          spatial_data POINT, -- 지리 정보 타입
                          PRIMARY KEY (location_id)
);

-- 2. store (가게) - (mission, review가 참조)
CREATE TABLE store (
                       store_id BIGINT NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       manager_number BIGINT NOT NULL, -- 신규 추가 반영
                       detail_address VARCHAR(255) NOT NULL,
                       location_id BIGINT NOT NULL,
                       PRIMARY KEY (store_id),
                       FOREIGN KEY (location_id) REFERENCES location(location_id)
);

-- 3. food (음식) - (member_food가 참조)
CREATE TABLE food (
                      food_id BIGINT NOT NULL,
                      name ENUM('KOREAN', 'JAPANESE', 'CHINESE', 'WESTERN', 'OTHER') NOT NULL,
                      PRIMARY KEY (food_id)
);

-- 4. term (약관) - (member_term이 참조)
CREATE TABLE term (
                      term_id BIGINT NOT NULL,
                      name ENUM('AGE', 'SERVICE', 'PRIVACY', 'LOCATION', 'MARKETING') NULL, -- ENUM 타입 및 NULL 허용 반영
                      contents TEXT NOT NULL,
                      PRIMARY KEY (term_id)
);

-- 5. member (사용자) - (mission, review, member_*, reply 등 다수가 참조)
CREATE TABLE member (
                        member_id BIGINT NOT NULL,
                        name VARCHAR(255) NOT NULL,
                        gender ENUM('MALE', 'FEMALE', 'NONE') NOT NULL,
                        birth DATE NOT NULL,
                        address VARCHAR(255) NOT NULL,
                        detail_address VARCHAR(255) NOT NULL,
                        social_uid VARCHAR(255) NOT NULL,
                        social_type ENUM('KAKAO', 'NAVER', 'APPLE', 'GOOGLE') NOT NULL,
                        point INT NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        phone_number VARCHAR(20) NULL,
                        deleted_at DATETIME NULL,
                        updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        PRIMARY KEY (member_id)
);

-- 6. mission (미션) - (member_mission이 참조)
CREATE TABLE mission (
                         mission_id BIGINT NOT NULL,
                         deadline DATE NOT NULL,
                         conditional_string VARCHAR(255) NOT NULL, -- NOT NULL 반영
                         point INTEGER NOT NULL,
                         created_at DATETIME NOT NULL,
                         store_id BIGINT NOT NULL,
                         PRIMARY KEY (mission_id),
                         FOREIGN KEY (store_id) REFERENCES store(store_id)
);

-- 7. review (리뷰) - (reply, review_photo가 참조)
CREATE TABLE review (
                        review_id BIGINT NOT NULL,
                        content TEXT NOT NULL,
                        created_at DATETIME NOT NULL,
                        star FLOAT NOT NULL,
                        store_id BIGINT NOT NULL,
                        user_id BIGINT NOT NULL, -- member_id 참조
                        PRIMARY KEY (review_id),
                        FOREIGN KEY (store_id) REFERENCES store(store_id),
                        FOREIGN KEY (user_id) REFERENCES member(member_id)
);

-- 8. member_food (사용자 선호 음식)
CREATE TABLE member_food (
                             member_food_id BIGINT NOT NULL,
                             member_id BIGINT NOT NULL,
                             food_id BIGINT NOT NULL,
                             PRIMARY KEY (member_food_id),
                             FOREIGN KEY (member_id) REFERENCES member(member_id),
                             FOREIGN KEY (food_id) REFERENCES food(food_id)
);

-- 9. member_term (사용자 약관 동의)
CREATE TABLE member_term (
                             member_term_id BIGINT NOT NULL,
                             member_id BIGINT NOT NULL,
                             term_id BIGINT NOT NULL,
                             PRIMARY KEY (member_term_id),
                             FOREIGN KEY (member_id) REFERENCES member(member_id),
                             FOREIGN KEY (term_id) REFERENCES term(term_id)
);

-- 10. member_mission (사용자 미션 수행 이력)
CREATE TABLE member_mission (
                                member_mission_id BIGINT NOT NULL,
                                is_complete BIT NOT NULL DEFAULT 0, -- Boolean/BIT 타입, NOT NULL 0 반영
                                mission_id BIGINT NOT NULL,
                                member_id BIGINT NOT NULL,
                                PRIMARY KEY (member_mission_id),
                                FOREIGN KEY (mission_id) REFERENCES mission(mission_id),
                                FOREIGN KEY (member_id) REFERENCES member(member_id)
);

-- 11. review_photo (리뷰 사진)
CREATE TABLE review_photo (
                              review_photo_id BIGINT NOT NULL,
                              photo_url VARCHAR(500) NULL, -- NULL 허용 반영
                              review_id BIGINT NOT NULL,
                              PRIMARY KEY (review_photo_id),
                              FOREIGN KEY (review_id) REFERENCES review(review_id)
);

-- 12. reply (리뷰 답글)
CREATE TABLE reply (
                       reply_id BIGINT NOT NULL,
                       content TEXT NOT NULL,
                       created_at DATETIME NOT NULL,
                       review_id BIGINT NOT NULL,
                       PRIMARY KEY (reply_id),
                       FOREIGN KEY (review_id) REFERENCES review(review_id)
);