package org.scoula.persistence;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import static org.junit.jupiter.api.Assertions.fail;

@Log4j
public class JDBCTest {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @DisplayName("JDBC 드라이버 연결이 된다.")
    public void testConnection() {
        String url= "jdbc:mysql://localhost:3306/scoula_db";
//        url 설정 후 데이터베이스 연결 객체를 로그에 출력한다.
        try(Connection con = DriverManager.getConnection(url, "scoula", "1234")) {
            log.info(con);
        } catch(Exception e) {
//            예외 발생 시 메시지 출력후 테스트를 실패 처리한다
            fail(e.getMessage());
        }
    }
}