package org.scoula.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Todo implements Cloneable{ // Cloneable 인터페이스를 구현하면 복제 가능
    private static int gid = 1;

    private int id;
    private String title;
    private String description;
    private boolean done;
    private Date date;

    @Builder
    public Todo(String title, String description, boolean done){
        // this를 쓴 이유는? this가 결국 Todo임
        // 여기에서 this는 또 다른 생성자를 호출한다
        // @AllArgsConstructor로 모든 파라미터를 받는 생성자를 만들었기 때문에 호출 가능
        this(gid++, title, description, done, new Date());
    }

//    객체를 안전하게 복제하기 위해서는 Cloneable 인터페이스까지 구현해야한다
    @Override
    public Object clone(){
        try{
            return super.clone(); // 얕은 복사
        }catch(CloneNotSupportedException e){
            // 해당 에러를 받아서 다른 에러로 처리한다
            throw new RuntimeException(e);
        }
    }

    public String getRegDate(){
        // 날짜 형식 지정
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
