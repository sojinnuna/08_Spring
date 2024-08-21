<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--sticky-top 클래스의 경우 스크롤시 상단에 고정시켜준다--%>
<nav class="navbar navbar-expand-md bg-primary navbar-dark sticky-top">
    <a class="navbar-brand" href="#"><i class="fa-solid fa-house"></i> Backend</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <!-- 좌측 메뉴 구성-->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#">메뉴1</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">메뉴2</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">메뉴3</a>
            </li>
        </ul>
        <!-- 우측 메뉴 -->
        <ul class="navbar-nav ms-auto"> <!-- "ms-auto" 클래스를 사용해 우측 정렬을 구현 -->
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <img src="https://randomuser.me/api/portraits/men/12.jpg" class="avatar-sm" alt="User Avatar" /> <!-- 이미지를 표시 -->
                    admin
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <i class="fa-solid fa-right-from-bracket"></i> 로그아웃
                </a>
            </li>
        </ul>
    </div>
</nav>
