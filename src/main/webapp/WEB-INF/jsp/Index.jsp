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
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700|Roboto:400,400i,700" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/icons.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/navigation.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/grid.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/footer.css"/>"/>
    </head>
</head>
<body>
    <div class="content">
    <%@include file="Navigation.jsp"%>
    <main class="content">
        <div class="index">
            <div class="index row">
                <div class="index col-12">
                    <h1>Tournament Manager</h1>
                </div>
            </div>
            <div class="index row">
                <div class="index col-6">
                    <a class="index__link" href="<c:url value="/tournaments/create"/>"></a>
                    <i class="material-icons index__icon">create</i>
                    <h2>Create a Tournament</h2>
                </div>
                <div class="index col-6">
                    <a class="index__link" href="<c:url value="/tournaments?id=0"/>"></a>
                    <i class="material-icons index__icon">list</i>
                    <h2>View Tournaments</h2>
                </div>
            </div>
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
