package org.scoula.todo.command;

import org.scoula.lib.cli.command.Command;
import org.scoula.lib.cli.ui.Input;
import org.scoula.todo.dao.TodoDao;
import org.scoula.todo.dao.TodoListDao;
import org.scoula.todo.domain.Todo;

// 메뉴2. 상세 Command 클래스
public class DetailTodoCommand implements Command {
    TodoDao dao = TodoDao.getInstance();

    @Override
    public void execute(){
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
}
