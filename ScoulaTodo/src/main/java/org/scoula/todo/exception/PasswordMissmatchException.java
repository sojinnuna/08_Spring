package org.scoula.todo.exception;

public class PasswordMissmatchException extends Exception{
    public PasswordMissmatchException(){
        super("이미 사용중인 사용자 ID 입니다.");
    }
}
