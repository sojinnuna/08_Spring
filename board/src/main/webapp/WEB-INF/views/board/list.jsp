<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<%@include file="../layouts/header.jsp"%>
<h1 class="page-header my-4"><i class="fas fa-list"></i> 글 목록</h1>
<table class="table table-hover">
    <thead>
    <tr>
        <th style="width: 60px">No</th>
        <th>제목</th>
        <th style="width: 100px">작성자</th>
        <th style="width: 130px">등록일</th>
    </tr>
    </thead>
    <tbody>
<%--    service의 getList 호출 -> mapper의 getList 호출 -> 테이블 전체를 리스트로 조회 --%>
    <c:forEach var="board" items="${list}">
        <tr>
<%--            속성을 가져올때는 getter로 접근 --%>
            <td>${board.no}</td>
<%--    쿼리 스트링으로 no 값이 들어가도록 링크 설정 --%>
            <td>
                <a href="get?no=${board.no}">${board.title}</a>
            </td>
            <td>${board.writer}</td>
            <td>
<%--                원하는 형식으로 날짜를 출력하기 위해 fmt 사용 --%>
                <fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="text-right">
    <a href="create" class="btn btn-primary">
        <i class="far fa-edit"></i>
        글쓰기
    </a>
</div>

<%@include file="../layouts/footer.jsp"%>

