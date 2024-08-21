package org.scoula.security.account.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUser extends User {

    private MemberVO member; // 실질적인 사용자 데이터
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(MemberVO vo) {
//        MemberVO의 사용자명, 비밀번호, 권한 목록만 추출해서 부모 클래스 호출
        super(vo.getUsername(),vo.getPassword(),vo.getAuthList());
        this.member = vo;
    }
}