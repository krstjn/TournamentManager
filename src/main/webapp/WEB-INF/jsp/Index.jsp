<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">

    <head>
        <title>Project Title</title>
        <meta charset="UTF-8">
    </head>
    <body>

    <h1>HBV501G Project Spring Boot Skeleton</h1>
    <p>This skeleton of a Spring Boot Web project was made to help groups get started on their projects without to much hassle.</p>

    <ul>
        <li><a href="/postit">Click here for Persistence Layer Demo</a></li>
    </ul>
    <sf:form method="POST" modelAttribute="tournament" action="/">

        <table>
            <tr>
                <td> Name:</td>
                    <%--the `path` attribute matches the `name` attribute of the Entity that was passed in the model--%>
                <td><sf:input path="name" type="text" placeholder="Nafn móts"/></td>
            </tr>
            <tr>
                <td>Hámarks fjöldi liða:</td>
                    <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
                <td><sf:input path="maxTeams" type="number"/></td>
            </tr>
            <tr>
                <td>Skráningarfrestur:</td>
                    <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
                <td><sf:input path="signUpExpiration" type="date"/></td>
            </tr>
            <tr>
                <td>Tegund móts: ${tournament.type}</td>
                    <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
                <td><sf:radiobutton path="type" value="Knockout"/>Knockout</td>
                <td><sf:radiobutton path="type" value="GroupStage"/>Group stage</td>
            </tr>
        </table>

        <input type="submit" VALUE="Búa til mót"/>

    </sf:form>
    </body>
    <footer>Class HBV501G, University of Iceland</footer>
</html>
