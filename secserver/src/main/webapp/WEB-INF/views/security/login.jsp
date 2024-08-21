<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>login</h1>
<%--SecurityConfig에서 loginProcessingUrl() 메소드에 넣은 url 경로와 action 값이 일치한다--%>
<form name='f' action='/security/login' method='POST'>
    <%--  csrf 토큰을 POST로 날려서 인증 정보를 보낸다--%>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'></td>
        </tr>
        <tr>
            <td colspan='2'>
                <input name="submit" type='submit' value="Login">
            </td>
        </tr>
    </table>
</form>
</body>
</html>

</html>