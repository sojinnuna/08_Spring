package org.scoula.todo.command;

import org.scoula.lib.cli.command.Command;
import org.scoula.todo.dao.TodoDao;
import org.scoula.todo.domain.Todo;

// 메뉴1. 목록의 Command 클래스
public class PrintTodoCommand implements Command {
    TodoDao dao = TodoDao.getInstance();

    @Override
    public void execute(){
        for(Todo todo: dao.getList()){
//            todo의 id는 gid로 객체 생성시 1씩 더해서 삽입해줌
            String line = "%2d] %s".formatted(todo.getId(), todo.getTitle());
            System.out.println(line);
        }
        System.out.println();
    }
}
