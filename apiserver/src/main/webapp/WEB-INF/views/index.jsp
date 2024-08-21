<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<body>
<h1>환영합니다.</h1>

<%-- isAnonymous(): 로그인이 안된 경우 True, 로그아웃되어있는 상태를 의미함 --%>
<sec:authorize access="isAnonymous()"> <!-- 로그인 안한 경우 -->
    <a href="/security/login">로그인</a>
</sec:authorize>

<%-- isAuthenticated(): 로그인이 된 경우 True, 로그인되어있는 상태를 의미함 --%>
<sec:authorize access="isAuthenticated()"> <!-- 로그인한 경우 -->
<%--    로그인한 사용자의 정보에 접근해서 username을 출력한다 --%>
    <sec:authentication property="principal.username"/>
    <form action="/security/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" value="로그아웃"/>
    </form>
</sec:authorize>

</body>
</html>