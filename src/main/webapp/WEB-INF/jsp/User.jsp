<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

    <head>
        <title>User Page</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
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
                <c:choose>
                    <c:when test="${!isAuthenticated}">
                        <a href="/login" class="navbar--item"><i class="material-icons md-light">lock</i>Login</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/user" class="navbar--item"><i class="material-icons md-light">account_circle</i>${username}</a>
                        <a href="/logout" class="navbar--item">Logout</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
    <h1>User Page</h1>
    <p>Here could be some user information</p>

    <table border="1px gray">
        <thead>
            <tr style="font-weight: 600;">
                <td>Name</td>
                <td>Job</td>
                <td>email</td>
                <td>Description</td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${name}</td>
                <td>${job}</td>
                <td>${email}</td>
                <td>${description}</td>
            </tr>
        </tbody>
    </table>

    <p>Logged in as ${username}. Logged in: ${isAuthenticated}<a href="<c:url value="/logout" />">Logout</a></p>
    </body>

</html>