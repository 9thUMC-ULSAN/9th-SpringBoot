-- 사용자가 리뷰를 작성하는 쿼리 (가게 ID, 사용자 ID, 별점, 내용)
INSERT INTO Review (user_id, store_id, rating, content, created_at)
VALUES (:userId, :storeId, :rating, :content, NOW());