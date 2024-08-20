# member 테이블을 기준으로 아우터 조인시켰으므로 권한 정보가 없는 사용자도 조회된다
# username이 'admin'인 사용자의 정보를 권한과 함께 조회
select m.username, password, email, reg_date, update_date, auth
from
    tbl_member m left outer join tbl_member_auth a
                                 on m.username = a.username
where m.username = 'admin';