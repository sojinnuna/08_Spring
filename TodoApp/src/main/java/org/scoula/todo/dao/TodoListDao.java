package org.scoula.todo.dao;

import org.scoula.todo.domain.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoListDao implements TodoDao {
    //private 생성자와 getInstance로 객체 리턴 => 싱글톤 패턴
    private static TodoListDao instance = new TodoListDao();
    public static TodoDao getInstance(){
        return instance;
    }
    private List<Todo> list;
    private TodoListDao(){
        list = new ArrayList<>();// 임시 테스트 데이터 구성
        for(int i = 0; i < 10; i++){
            // 빌더 사용해서 열개의 객체 생성 후 리스트에 추가
            Todo todo = Todo.builder()
                    .title("Todo "+i)
                    .description("Description "+i)
                    .done(false)
                    .build();
            list.add(todo);
        }
    }

//    전체 할일 목록 가져오는 메소드 (Read)
    @Override
    public List<Todo> getList(){
        return list;
    }

//    해당 아이디의 할일 가져오는 메소드 (Read)
    @Override
    public Todo getTodo(int id){
        for(Todo todo: list){
//            리스트를 돌면서 받아온 id 값과 일치하는 todo 찾기
            if(todo.getId() == id){
                return todo;
            }
        }
        return null;
    }

    // 리스트에 새로운 todo 추가하는 메소드 (Create)
    @Override
    public void add(Todo todo){
        list.add(todo);
    }


//    해당 todo를 새로운 값으로 변경해주는 메소드(Update)
    @Override
    public void update(Todo todo){
        for(int i =0; i < list.size(); i++){
            if(todo.getId() == list.get(i).getId()){
//                리스트를 돌면서 id를 비교해서 해당 id의 todo를 새로운 todo로 변경
                list.set(i, todo);
            }
        }
    }

//     해당 아이디의 todo를 리스트에서 삭제해주는 메소드 (Delete)
    @Override
    public void delete(int id){
        for(int i = 0; i < list.size(); i++){
//            리스트에서 해당 todo를 찾아서 삭제해준다
            if(list.get(i).getId() == id){
                list.remove(i);
                return;
            }
        }
    }
}
