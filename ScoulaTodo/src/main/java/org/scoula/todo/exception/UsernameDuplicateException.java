package org.scoula.todo.exception;

public class UsernameDuplicateException extends Exception{
    public UsernameDuplicateException(){
        super("비밀번호가 맞지 않습니다.");
    }
}
