package org.scoula.todo.dao;

import org.scoula.todo.domain.Todo;

import java.util.List;

public interface TodoDao {
    //    전체 할일 목록 가져오는 메소드 (Read)
    List<Todo> getList();

    //    해당 아이디의 할일 가져오는 메소드 (Read)
    Todo getTodo(int id);

    // 리스트에 새로운 todo 추가하는 메소드 (Create)
    void add(Todo todo);

    //    해당 todo를 새로운 값으로 변경해주는 메소드(Update)
    void update(Todo todo);

    //     해당 아이디의 todo를 리스트에서 삭제해주는 메소드 (Delete)
    void delete(int id);
}
