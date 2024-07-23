package org.scoula.todo.dao;

import org.scoula.todo.common.JDBCUtil;
import org.scoula.todo.domain.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// UserDao 인터페이스를 구현하여 사용자 정보에 대한 실제 CRUD 작업 수행
public class UserDaoImpl implements UserDao {
    // JDBC 연결 객체를 생성해서 초기화
    Connection conn = JDBCUtil.getConnection();

    // USERS 테이블 관련 SQL 명령어
    private String USER_LIST = "SELECT * FROM users";
    private String USER_GET = "SELECT * FROM users WHERE id =?";
    private String USER_INSERT = "INSERT INTO users(id, password, name, role) VALUES(?,?,?,?)";
    private String USER_UPDATE = "UPDATE users SET name=?, role=? WHERE id=?";
    private String USER_DELETE = "DELETE FROM users WHERE id = ?";


    // 회원 등록
    @Override
    public int create(UserVO user) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(USER_INSERT)) {
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getRole());
            return stmt.executeUpdate();
        }
    }

    // ResultSet을 UserVO 객체로 매핑(변환)하는 메소드
    private UserVO map(ResultSet rs) throws SQLException {
        UserVO user = new UserVO();
        user.setId(rs.getString("ID"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setName(rs.getString("NAME"));
        user.setRole(rs.getString("ROLE"));
        return user;
    }

    // 회원 전체 목록 조회
    @Override
    public List<UserVO> getList() throws SQLException {
        List<UserVO> userList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(USER_LIST);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                UserVO user = map(rs);
                userList.add(user);
            }
        }
        return userList;
    }

    // 특정 아이디를 가진 회원 조회
    @Override
    public Optional<UserVO> get(String id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(USER_GET)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        }
        // 해당 아이디를 가진 회원이 없을 때 도달하는 코드
        return Optional.empty();
    }

    // 회원 수정
    @Override
    public int update(UserVO user) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(USER_UPDATE)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getRole());
            stmt.setString(3, user.getId());
            return stmt.executeUpdate();
        }
    }

    // 회원 삭제
    @Override
    public int delete(String id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(USER_DELETE)) {
            stmt.setString(1, id);
            return stmt.executeUpdate();
        }
    }
}
