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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,700|Roboto:400,400i,700" rel="stylesheet">        <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/navigation.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/icons.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/input.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/grid.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/footer.css"/>"/>
</head>
<body>
<div class="content">
<nav class="navbar">
    <div class="navbar--container">
        <div class="navbar--heading">
            <a href="/"><i class="material-icons md-light md-36">home</i></a>
        </div>
        <div class="navbar--nav">
            <c:choose>
                <c:when test="${!isAuthenticated}">
                    <a href="/login" class="navbar--item"><i class="material-icons md-light">lock</i>Login</a>
                </c:when>
                <c:otherwise>
                    <a href="/profile" class="navbar--item"><i class="material-icons md-light">account_circle</i>${username}</a>
                    <a href="/logout" class="navbar--item">Logout</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>
<main>
    <div class="container">
        <div class="col-6 center">
            <h3>${title}</h3>
            <i class="material-icons md-96">account_circle</i>
            <c:if test="${not empty errors}">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li class="error">${error}</li>
                    </c:forEach>
                </ul>

            </c:if>
            <sf:form method="POST" action="${target}">
                <div class="center">
                    <input class="input" name="username" id="username" type="text" autocomplete="off" placeholder="Username"/>
                </div>
                <div class="center">
                    <input class="input" name="password" id="password" type="password" placeholder="Password"/>
                </div>
                <button class="btn btn-primary btn-medium">${title}</button>
            </sf:form>
            <c:if test="${title == 'Login'}">
                <p>Not a user? <a href="/signup">SignUp</a></p>
            </c:if>
        </div>
    </div>
</main>
<script>
    $('#username').on("keypress", function(e){
        e.preventDefault();
        var charCode = e.charCode;
        if ((charCode > 47 && charCode < 58 ) ||
            (charCode > 64 && charCode < 90 ) ||
            (charCode > 96 && charCode < 123)){
            var str = $('#username').val() + e.key.toUpperCase();
            $('#username').val(str.toUpperCase());
        }
    });
</script>
</div>
</body>
<footer class="footer">
    <div class="footer--container">
        <div class="footer--item">Class HBV501G, University of Iceland</div>
        <div class="footer--item">Class HBV501G, University of Iceland</div>
        <div class="footer--item">Class HBV501G, University of Iceland</div>
    </div>
</footer>
</html>
