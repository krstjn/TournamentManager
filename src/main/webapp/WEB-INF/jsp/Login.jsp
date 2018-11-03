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
</head>
<body>
<nav class="navbar">
    <div class="navbar--container">
        <div class="navbar--heading">
            <a href="/"><i class="material-icons md-light md-36">home</i></a>
        </div>
        <div class="navbar--nav">
            <a href="/" class="navbar--item"><i class="material-icons md-light">calendar_today</i></a>
            <a href="/" class="navbar--item"><i class="material-icons md-light">menu</i></a>
            <a href="/" class="navbar--item"><i class="material-icons md-light">face</i></a>
            <a href="/" class="navbar--item"><i class="far fa-inverse fa-2x fa-user"></i></a>
            <a href="/" class="navbar--item"><i class="far fa-inverse fa-2x fa-calendar"></i></a>
            <a href="/" class="navbar--item"><i class="far fa-inverse fa-2x fa-comment"></i></a>
        </div>
    </div>
</nav>
<main>
    <div class="container">
        <div class="col-6 center">
            <h3>${title}</h3>
            <c:if test="${not empty errors}">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li class="error">${error}</li>
                    </c:forEach>
                </ul>

            </c:if>
            <sf:form method="POST" action="${target}">
                <div class="center">
                    <input class="input" name="username" id="username" type="text" placeholder="Username"/>
                </div>
                <div class="center">
                    <input class="input" name="password" id="password" type="password" placeholder="Password"/>
                </div>
                <button class="btn btn-medium">${title}</button>
            </sf:form>
            <c:if test="${title == 'Login'}">
                <p>Not a user? <a href="/signup">SignUp</a></p>
            </c:if>
        </div>
    </div>

</main>
</body>
<footer>Class HBV501G, University of Iceland</footer>
</html>
