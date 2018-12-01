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
    <%@include file="../Navigation.jsp"%>

<main>
        <div class="profile__info">
            <div class="col-12">
                <div class="row-6">
                    <i class="material-icons md-light">account_circle</i>
                </div>
                <div class="row-6">
                    <h1>${username}</h1>
                </div>
            </div>
        </div>
        <div class="col-12">
            <h1>My tournaments</h1>
        </div>
        <c:choose>
            <c:when test="${not empty tournaments}">
                <div class="tournaments">
                    <div class="col-12">
                        <c:forEach var="tournament" items="${tournaments}">
                                <div class="row-12">
                                    <a class="tournaments__link" href="/tournaments?id=${tournament.id}"></a>
                                    <div class="tournaments__info">
                                        <h2>${tournament.name}</h2>
                                        <c:choose>
                                            <c:when test="${tournament.teams.size() < tournament.maxTeams  && tournament.matches.size() == 0}">
                                                <div class="open">Open for registration</div>
                                            </c:when>
                                            <c:when test="${tournament.matches.size() > 0}">
                                                <div class="started">Started</div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="closed">Closed for registration</div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                        </c:forEach>
                    </div>
                </div>
            </c:when>

            <%--If all tests are false, then do this--%>
            <c:otherwise>
                <h3>No tournaments!</h3>
            </c:otherwise>
        </c:choose>
</main>
</div>
</body>
<%@include file="../Footer.jsp"%>

</html>
