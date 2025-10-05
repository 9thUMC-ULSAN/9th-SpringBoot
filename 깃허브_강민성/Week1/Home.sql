-- 현재 선택된 지역에서 도전 가능한 미션 목록을 가져오는 쿼리
SELECT
    M.id,
    M.name,
    M.description,
    M.required_amount,
    M.reward_point,
    S.name AS store_name
FROM Mission M
JOIN Store S ON M.store_id = S.id
LEFT JOIN UserMission UM ON M.id = UM.mission_id AND UM.user_id = :userId
WHERE
    S.area_id = :areaId
    AND UM.status IS NULL OR UM.status = 'IN_PROGRESS' -- 완료 상태가 아닌 미션만
ORDER BY M.id DESC
LIMIT :pageSize OFFSET :offset;