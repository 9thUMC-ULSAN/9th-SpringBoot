 -- 1번 --
select
    um.status as mission_status,
    m.name as mission_name,
    m.description as mission_description,
    m.point as reward_point,
    s.name as store_name,
    um.updated_at
from
    user as u
join
    user_mission as um on u.id = um.user_id
join
    mission as m on um.mission_id = m.id
join
    store as s on m.store_id = s.id
where
    u.id = '찾을 유저'
    AND um.status = '진행 중'
    AND (um.updated_at, um.id) < ('마지막으로 본 미션 upload', id)
order by
    um.updated_at desc
limit 15;


----2번----
select
    um.status as mission_status,
    m.name as mission_name,
    m.description as mission_description,
    m.point as reward_point,
    s.name as store_name,
    um.updated_at
from
    user as u

join
    user_mission as um on u.id = um.user_id
join
    mission as m on um.mission_id = m.id
join
    store as s on m.store_id = s.id
where
    u.id = '찾을 유저'
    AND um.status = '진행완료'
    AND (um.updated_at, um.id) < ('마지막으로 본 미션 upload', id)
order by
    um.updated_at desc, um.id desc
limit 15;


----3번----

리뷰를 작성하는 쿼리에서 그냥 insert문만 작성해서 처리하는 것인지 의도를 잘 모르겠어서
찾아봄

인서트하는 과정에서 중간에 절대 끊어져서는 안되는 작업을 트랜잭션
처리를 함으로써 데이터 베이스의 데이터를 항상 정확하고 일관되게 유지

=> 트랜젝션으로 묶어서 모두 성공하거나, 하나라도 실패하면 모두 없던 일로 되돌림 

begin; -- 트랜젝션으로 묶는 시작점

insert into review(user_id, store_id, body, score)
values(1234, 2, '음 너무 맜있어요~~', 5);

update store set score = (select avg(score) from review where store_id = 2)
where id = 2;

commit; 

-- 4번 --
select
    u.nickname as nickname,
    u.email as email,
    u.phone_num as phone_num,
    u.point as point
from
    user as u
where id = '현재 유저'