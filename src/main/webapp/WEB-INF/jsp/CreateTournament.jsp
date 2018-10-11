<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">

    <head>
        <title>Create tournament</title>
        <meta charset="UTF-8">
    </head>
    <body>
    <sf:form method="POST" modelAttribute="tournament" action="/createTournament">

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
            <td>Tegund móts:</td>
                <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
            <td><sf:radiobutton path="type" value="Knockout"/>Knockout</td>
            <td><sf:radiobutton path="type" value="GroupStage"/>Group stage</td>
        </tr>
    </table>

    <input type="submit" VALUE="Búa til mót"/>
    </sf:form>
    <a href="/">Forsíða</a>
    </body>
    <footer>Class HBV501G, University of Iceland</footer>
</html>
