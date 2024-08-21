package org.scoula.todo.dao;


import org.scoula.todo.domain.UserVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// DAO: Data Access Object
// 테이블에 대한 CRUD 연산을 처리하는 인터페이스
public interface UserDao {
//    새로운 사용자 등록 (CRUD 중 C)
    int create(UserVO user) throws SQLException;

//    모든 사용자 목록 조회 (CRUD 중 R)
    List<UserVO> getList() throws SQLException;

//    특정 사용자 정보 조회 (CRUD 중 R)
//    존재하지 않는 경우 빈 Optional 객체 반환
    Optional<UserVO> get(String id) throws SQLException;

//    사용자의 정보 수정 (CRUD 중 U)
    int update(UserVO user) throws SQLException;

//        특정 사용자 삭제(CRUD 중 D)
    int delete(String id) throws SQLException;

}
