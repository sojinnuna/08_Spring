package org.scoula.todo.command;

import org.scoula.lib.cli.command.Command;
import org.scoula.lib.cli.ui.Input;
import org.scoula.todo.dao.TodoDao;
import org.scoula.todo.dao.TodoListDao;
import org.scoula.todo.domain.Todo;

// 메뉴4. 수정 Command 클래스
public class UpdateTodoCommand implements Command {
    TodoDao dao = TodoListDao.getInstance();

    @Override
    public void execute() {int id = Input.getInt("수정할 Id: ");
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
}
