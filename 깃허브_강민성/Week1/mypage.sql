2-- 특정 사용자의 마이 페이지 정보를 가져오는 쿼리
 SELECT nickname, email, phone_number, point
 FROM User
 WHERE id = :userId;