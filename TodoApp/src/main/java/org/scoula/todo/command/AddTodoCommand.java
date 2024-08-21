package org.scoula.todo.command;

import org.scoula.lib.cli.command.Command;
import org.scoula.lib.cli.ui.Input;
import org.scoula.todo.dao.TodoDao;
import org.scoula.todo.dao.TodoListDao;
import org.scoula.todo.domain.Todo;

// 메뉴3. 추가 Command 클래스
public class AddTodoCommand implements Command {
    TodoDao dao = TodoListDao.getInstance();

    @Override
    public void execute() {
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


}
