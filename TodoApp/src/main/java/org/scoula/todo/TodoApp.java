package org.scoula.todo;

import org.scoula.lib.cli.App;
import org.scoula.lib.cli.ui.Menu;
import org.scoula.lib.cli.ui.MenuItem;
import org.scoula.todo.command.AddTodoCommand;
import org.scoula.todo.command.DetailTodoCommand;
import org.scoula.todo.command.PrintTodoCommand;
import org.scoula.todo.command.UpdateTodoCommand;
import org.scoula.todo.service.TodoService;

public class TodoApp extends App {
    // Command를 모두 service 클래스 내에 메소드로 캡슐화
    TodoService service = new TodoService();

    @Override
    public void createMenu(Menu menu){
        super.createMenu(menu);

        // 메소드 참조를 통해 service 객체의 메소드 사용
        menu.add(new MenuItem("목록", service::printTodoList));
        menu.add(new MenuItem("상세", service::detailTodo));
        menu.add(new MenuItem("추가", service::addTodo));
        menu.add(new MenuItem("수정", service::updateTodo));
        menu.add(new MenuItem("삭제", service::deleteTodo));
    }
    public static void main(String[] args) {
        App app = new TodoApp();
        app.run();
    }
}
