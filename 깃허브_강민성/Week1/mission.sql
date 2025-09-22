-- 특정 사용자의 진행 중/완료한 미션 목록을 가져오는 쿼리
SELECT
    M.name,
    M.description,
    M.reward_point,
    UM.status,
    S.name AS store_name,
    UM.completed_date
FROM UserMission UM
JOIN Mission M ON UM.mission_id = M.id
JOIN Store S ON M.store_id = S.id
WHERE UM.user_id = :userId
ORDER BY UM.completed_date DESC, UM.status ASC
LIMIT :pageSize OFFSET :offset;