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
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/grid.css"/>"/>
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
        <div class="row">
            <div class="col-12">
                <h1>Tournament Manager</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-6">
                <li><a href="<c:url value="/tournaments/create"/>">Create a Tournament</a></li>
            </div>
            <div class="col-6">
                <li><a href="<c:url value="/tournaments"/>">View Tournaments</a></li>
            </div>
        </div>
    </main>

    </body>
    <footer>Class HBV501G, University of Iceland</footer>
</html>
