package org.scoula.config;

import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
class RootConfigTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private DataSource dataSource;
    @Test
    @DisplayName("DataSource 연결이 된다.")
    public void dataSource() throws SQLException {
        try(Connection con = dataSource.getConnection()){
            log.info("DataSource 준비 완료");
            log.info(con);
        }
    }

    @Test
    public void testSqlSessionFactory() {
        try (
//                공장에서 Session 만들고 불러오기
                SqlSession session = sqlSessionFactory.openSession(); // sqlSessionFactory로 SQLSession 염
                Connection con = session.getConnection(); // SQLSession로 Connection 염
        ) {
            log.info(session);
            log.info(con);
        } catch (Exception e) {
//            예외 발생시 테스트 실패 처리
            fail(e.getMessage());
        }
    }


}