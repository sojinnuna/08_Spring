package org.scoula.todo.service;

import org.scoula.lib.cli.ui.Input;
import org.scoula.todo.dao.TodoDao;
import org.scoula.todo.dao.TodoListDao;
import org.scoula.todo.domain.Todo;

import java.util.function.Supplier;
public class TodoService {
    TodoDao dao = TodoListDao.getInstance();

    public void detailTodo(){
//         사용자에게 찾을 todo의 id 입력받기
        int id = Input.getInt("Todo Id: ");
        Todo todo = dao.getTodo(id);

        System.out.println("[Todo 상세보기]---------------------------------");
        System.out.println("ID : " + todo.getId());
        System.out.println("제목 : " + todo.getTitle());
        System.out.println("설명 : " + todo.getDescription());
        System.out.println("완료여부: " + todo.isDone());
        System.out.println("등록일 : " + todo.getRegDate());
        System.out.println("----------------------------------------------");
        System.out.println();

    }

    public void addTodo() {
        System.out.println("[새 Todo 추가]---------------------------------");
        String title = Input.getLine("제목: ");
        String description = Input.getLine("설명: ");
        System.out.println("---------------------------------");

//        사용자한테 받아온 제목과 설명으로 새로운 할일 생성
        Todo todo = Todo.builder()
                .title(title)
                .description(description)
                .done(false)
                .build();
//        할일 목록에 추가
        dao.add(todo);

        System.out.println();
    }

    public void updateTodo() {
        int id = Input.getInt("수정할 Id: ");
        // 해당 id를 가진 todo를 찾아옴
        Todo todo = dao.getTodo(id);

        System.out.println("[Todo 수정하기]---------------------------------");
        System.out.println("ID    :" + todo.getId());
//        값을 입력하지 않으면 원래 제목, 입력하면 새로운 제목으로 return
        String title = Input.getLine("제목", todo.getTitle());
//        값을 입력하지 않으면 원래 설명, 입력하면 새로운 설명으로 return
        String description = Input.getLine("설명", todo.getDescription());
//        값을 입력하지 않으면 원래 완료여부, 입력하면 새로운 완료여부로 return
        Boolean done = Input.confirm("완료여부", todo.isDone());
        System.out.println("---------------------------------");
        System.out.println();

//        찾아온 todo를 새로 복제함
        Todo updateTodo = (Todo)todo.clone();
        updateTodo.setTitle(title);
        updateTodo.setDescription(description);
        updateTodo.setDone(done);

        // 업데이트된 내용을 가진 todo로 리스트를 갈아끼움
        dao.update(updateTodo);
    }

    public void printTodoList(){
        for(Todo todo: dao.getList()){
//            todo의 id는 gid로 객체 생성시 1씩 더해서 삽입해줌
            String line = "%2d] %s".formatted(todo.getId(), todo.getTitle());
            System.out.println(line);
        }
        System.out.println();
    }

    public void deleteTodo(){
        int id = Input.getInt("삭제할 Todo Id: ");
        dao.delete(id);

        System.out.println();
    }
}


