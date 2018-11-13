<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">

<head>
    <head>
        <title>Tournaments</title>
        <meta charset="UTF-8">
        <script src="<c:url value="/js/jquery-3.3.1.min.js" />"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700|Roboto:400,400i,700" rel="stylesheet">        <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/icons.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/grid.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/navigation.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/profile.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/footer.css"/>"/>
    </head>
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
        <c:choose>
            <%--If the model has an attribute with the name `tournaments`--%>
            <c:when test="${not empty tournaments}">
                <%--Create a table for the Postit Notes--%>
                <div class="tournaments">

                    <c:forEach var="tournament" items="${tournaments}">
                        <div class="row">
                            <p>${tournament.name}</p>
                            <p>Fjöldi skráða liða: ${tournament.teams.size()}</p>
                        </div>
                    </c:forEach>
                </div>
            </c:when>

            <%--If all tests are false, then do this--%>
            <c:otherwise>
                <h3>No tournaments!</h3>
            </c:otherwise>
        </c:choose>
    </div>
</main>
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
