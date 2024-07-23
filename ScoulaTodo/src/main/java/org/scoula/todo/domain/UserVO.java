package org.scoula.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// VO: Value Object (테이블의 한 행을 매핑)
public class UserVO {
    private String id;
    private String password;
    private String name;
    private String role;

}
