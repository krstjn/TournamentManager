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
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/icons.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/navigation.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>

    </head>
</head>
<body>
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
        <h1>HBV501G Project Spring Boot Skeleton: Is authenticated: ${isAuthenticated}. Username: ${username}</h1>
        <p>This skeleton of a Spring Boot Web project was made to help groups get started on their projects without to much
            hassle.</p>

        <ul>
            <li><a href="<c:url value="/tournaments/create"/>">Create a tournament</a></li>
        </ul>
        <c:choose>
            <%--If the model has an attribute with the name `tournaments`--%>
            <c:when test="${not empty tournaments}">
                <%--Create a table for the Postit Notes--%>
                <div class="tournaments">

                    <c:forEach var="tournament" items="${tournaments}">
                        <div>
                            <p><a href="/tournament?id=${tournament.id}"> ${tournament.name}</a></p>
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
    </main>

    </body>
    <footer>Class HBV501G, University of Iceland</footer>
</html>
