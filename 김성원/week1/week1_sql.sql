-- 리뷰 작성하기 
INSERT INTO review (review, star, created_at, user_id, shop_id)
SELECT ?, ?, NOW(), um.user_id, m.shop_id
FROM user_mission um
         JOIN mission m ON um.mission_id2 = m.mission_id
WHERE um.user_mission_id = ?       -- 해당 user_mission_id 바인딩
  AND um.user_id = ?               -- 로그인한 사용자
  AND um.is_complete = true
  AND NOT EXISTS (
    SELECT 1
    FROM review r
    WHERE r.user_id = um.user_id
      AND r.shop_id = m.shop_id
      AND r.review_id = um.user_mission_id
);

-- 마이페이지 화면 쿼리
-- 기본 회원 정보
SELECT u.user_id, u.name, u.email, u.phone_number
FROM user u
WHERE u.user_id = ?;

-- 내 포인트
SELECT COALESCE(SUM(p.point), 0) AS total_point
FROM user u
LEFT JOIN (SELECT user_id, point FROM user WHERE user_id = ?) p
ON u.user_id = p.user_id
WHERE u.user_id = ?;

-- 내가 작성한 리뷰
SELECT r.review_id, r.review, r.star, r.created_at, s.shop_name
FROM review r
JOIN shop s ON r.shop_id = s.shop_id
WHERE r.user_id = ?
ORDER BY r.created_at DESC;

-- 내가 진행 중 / 완료한 미션 보기 (페이징)
SELECT t.user_mission_id,
       t.mission_id,
       t.shop_name,
       t.point,
       t.sort_at
FROM (
         SELECT um.user_mission_id,
                m.mission_id,
                s.shop_name,
                m.point,
                CASE
                    WHEN um.is_complete = true THEN um.updatedAt
                    ELSE COALESCE(um.updatedAt, um.createdAt)
                END AS sort_at
         FROM user_mission um
         JOIN mission m ON um.mission_id2 = m.mission_id
         JOIN shop s ON m.shop_id = s.shop_id
         WHERE um.user_id = ?
           AND (um.is_complete = true OR um.is_complete = false)
     ) t
WHERE (t.sort_at < ? OR (t.sort_at = ? AND t.user_mission_id < ?))
ORDER BY t.sort_at DESC, t.user_mission_id DESC
LIMIT ?;

-- 홈 화면 쿼리
SELECT COUNT(*) AS completed_count, 10 AS goal
FROM user_mission um
JOIN mission m ON um.mission_id2 = m.mission_id
WHERE um.user_id = ?
  AND m.local_id = ?
  AND um.is_complete = true;


SELECT t.mission_id, t.title, t.shop_name, t.point, t.ends_at,
       CASE
           WHEN t.ends_at IS NULL THEN NULL
           ELSE GREATEST(DATEDIFF(t.ends_at, NOW()), 0)
       END AS days_left,
       t.sort_at
FROM (
         SELECT m.mission_id, m.title, m.point, m.ends_at, s.shop_name,
                COALESCE(m.created_at, m.deadline) AS sort_at
         FROM mission m
         JOIN shop s ON s.shop_id = m.shop_id
         WHERE m.local_id = ?
           AND (m.deadline IS NULL OR m.deadline > NOW())
           AND NOT EXISTS (
             SELECT 1
             FROM user_mission um
             WHERE um.mission_id2 = m.mission_id
               AND um.user_id = ?
         )
     ) t
WHERE (t.sort_at < ? OR (t.sort_at = ? AND t.mission_id < ?))
ORDER BY t.sort_at DESC, t.mission_id DESC
    LIMIT ?;