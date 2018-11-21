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
    <%@include file="Navigation.jsp"%>

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
<%@include file="Footer.jsp"%>

</html>
