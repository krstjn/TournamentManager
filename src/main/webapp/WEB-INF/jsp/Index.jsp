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
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/navigation.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>
    </head>
</head>
<body>
    <div class="navbar">
        <div class="navbar--container">
            <div class="navbar--heading">
                <a href="/"><img class="svg" src="<c:url value="/img/home-48.svg"/>" alt="Forsíða"></a>
            </div>
            <div class="navbar--nav">
                <a href="/" class="navbar--item"><img class="svg" src="<c:url value="/img/user-30.svg"/>" alt="Menu"></a>
                <a href="/" class="navbar--item"><img class="svg" src="<c:url value="/img/calendar-30.svg"/>" alt="Cal"></a>
                <a href="/" class="navbar--item"><img class="svg" src="<c:url value="/img/hamburger-30.svg"/>" alt="Menu"></a>
            </div>
        </div>
    </div>
    <main>
        <h1>HBV501G Project Spring Boot Skeleton</h1>
        <p>This skeleton of a Spring Boot Web project was made to help groups get started on their projects without to much
            hassle.</p>

        <ul>
            <li><a href="/postit">Click here for Persistence Layer Demo</a></li>
            <li><a href="/createTournament">Create a tournament</a></li>
        </ul>
        <c:choose>
            <%--If the model has an attribute with the name `tournaments`--%>
            <c:when test="${not empty tournaments}">
                <%--Create a table for the Postit Notes--%>
                <div class="tournaments">

                    <c:forEach var="tournament" items="${tournaments}">
                        <div>
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
    </main>

    </body>
    <footer>Class HBV501G, University of Iceland</footer>
</html>
