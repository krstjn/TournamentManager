<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">

<head>
    <title>Create tournament</title>
    <meta charset="UTF-8">
    <script src="<c:url value="/js/jquery-3.3.1.min.js" />"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/navigation.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/icons.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>

</head>
<body>
<nav class="navbar">
    <div class="navbar--container">
        <div class="navbar--heading">
            <a href="/"><i class="material-icons md-light md-36">home</i></a>
        </div>
        <div class="navbar--nav">
            <a href="/login" class="navbar--item"><i class="material-icons md-light">lock</i>Login</a>
            <a href="/profile" class="navbar--item"><i class="material-icons md-light">face</i></a>
        </div>
    </div>
</nav>
<main>
    <div>
        <h2>Villa kom upp</h2>
        <c:if test="${not empty errorMsg}">
            <p>${errorMsg}</p>
        </c:if>
    </div>
</main>
</body>
<footer>Class HBV501G, University of Iceland</footer>
</html>
