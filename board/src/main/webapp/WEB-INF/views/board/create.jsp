<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<%@include file="../layouts/header.jsp"%>
<h1 class="page-header my-4"><i class="far fa-edit"></i> 새 글쓰기</h1>
<div >
    <form method="post">
        <div>
            <label>제목</label>
            <input name="title" class="form-control">
        </div>
        <div>
            <label>작성자</label>
            <input name="writer" class="form-control">
        </div>
        <div>
            <label>내용</label>
            <textarea class="form-control" name="content" rows="10"></textarea>
        </div>
<%--        제출 시 post 메소드인 create가 실행되면서 service의 create 실행후 목록으로 리다이렉트 --%>
        <button type="submit" class="btn btn-primary"><i class="fas fa-check"></i> 확인</button>
        <button type="reset" class="btn btn-primary"><i class="fas fa-undo"></i> 취소</button>
        <a href="list" class="btn btn-primary"><i class="fas fa-list"></i> 목록</a>
    </form>
    </div>
<%@include file="../layouts/footer.jsp"%>
