package org.scoula.todo;

import org.scoula.lib.cli.App;
import org.scoula.lib.cli.ui.Input;
import org.scoula.lib.cli.ui.Menu;
import org.scoula.lib.cli.ui.MenuItem;

import javax.swing.*;

public class TodoApp extends App {
    // 로그인한 상태의 메뉴
    Menu userMenu;
    // 로그아웃한 상태의 메뉴
    Menu annoymousMenu;
    
    @Override
    public void init(){
        // 메소드 참조를 통해서 메뉴 생성
        annoymousMenu = new Menu();
        annoymousMenu.add(new MenuItem("로그인", this::login));
        annoymousMenu.add(new MenuItem("가입", this::join));
        annoymousMenu.add(new MenuItem("종료", this::exit));

        userMenu = new Menu();
        userMenu.add(new MenuItem("로그아웃", this::logout));
        userMenu.add(new MenuItem("종료", this::exit));

        // 처음에는 로그아웃한 상태로 메뉴 보여주기
        setMenu(annoymousMenu);
    }


    private void logout() {
        // 사용자가 y와 n을 입력함에 따라 trye/false 반
        if(Input.confirm("로그아웃 할까요?")){
            setMenu(annoymousMenu);
        }
    }

    private void exit() {
    }

    private void join() {
    }

    private void login() {
        // 로그인 후에는 메뉴 교체
        setMenu(userMenu);
    }

    public static void main(String[] args) {
        // 변경할 수 없도록 final 선언
        final TodoApp app = new TodoApp();
        app.run();
    }
}
