package org.scoula.security.account.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

//member_auth 테이블과 매칭되는 VO
@Data
public class AuthVO implements GrantedAuthority {
    private String username;
    private String auth;

    @Override
    public String getAuthority() {
//        권한 정보만 추출해주는 메소드
        return auth;
    }

}