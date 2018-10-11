<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">

<head>
    <title>Project Title</title>
    <meta charset="UTF-8">
</head>
<body>

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
            <table class="tournaments">

                <c:forEach var="tournament" items="${tournaments}">
                    <tr>
                        <td>${tournament.name}</td>
                        <td>${tournament.teamCount}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>

    <%--If all tests are false, then do this--%>
        <c:otherwise>
            <h3>No notes!</h3>
        </c:otherwise>
    </c:choose>
    </body>
    <footer>Class HBV501G, University of Iceland</footer>
    </html>
