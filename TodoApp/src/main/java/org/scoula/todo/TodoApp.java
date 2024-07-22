package org.scoula.todo;

import org.scoula.lib.cli.App;
import org.scoula.lib.cli.ui.Menu;
import org.scoula.lib.cli.ui.MenuItem;
import org.scoula.todo.command.AddTodoCommand;
import org.scoula.todo.command.DetailTodoCommand;
import org.scoula.todo.command.PrintTodoCommand;
import org.scoula.todo.command.UpdateTodoCommand;

public class TodoApp extends App {
    @Override
    public void createMenu(Menu menu){
        super.createMenu(menu);

        menu.add(new MenuItem("목록", new PrintTodoCommand()));
        menu.add(new MenuItem("상세", new DetailTodoCommand()));
        menu.add(new MenuItem("추가", new AddTodoCommand()));
        menu.add(new MenuItem("수정", new UpdateTodoCommand()));
        menu.add(new MenuItem("삭제", new DetailTodoCommand()));
    }
    public static void main(String[] args) {
        App app = new TodoApp();
        app.run();
    }
}
