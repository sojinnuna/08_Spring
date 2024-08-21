<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Firefly</title>

    <%--    font-awesome css 삽입--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
    <%--    bootstrap css 삽입--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <%--    우리가 만든 css 삽입--%>
    <link rel="stylesheet" href="/resources/css/main.css" />
    <%--    bootstrap js 삽입--%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <%--    우리가 만든 js 삽입--%>
    <script src="/resources/js/main.js"></script> <!-- 여기를 수정했습니다 -->
</head>

<body>
<header class="jumbotron mb-0 rounded-0">
    <h1>Backend Study</h1>
    <p>자바 기반의 백엔드 기술을 배웁니다.</p>
</header>

<%@ include file="menu.jsp"%>
<div class="container my-5">
