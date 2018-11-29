<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<div lang="en">

    <head>
        <title>Tournament View</title>
        <meta charset="UTF-8">
        <script src="<c:url value="/js/jquery-3.3.1.min.js" />"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700|Roboto:400,400i,700" rel="stylesheet">        <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/navigation.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/TournamentView.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/icons.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/input.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/grid.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/footer.css"/>"/>

    </head>
    <body>
    <div class="content">
        <%@include file="Navigation.jsp"%>

        <main>
            <div class = titleSection>

                        <h1> ${tournament.name} </h1>

                        <c:if test="${isAuthenticated}">

                            <a href="tournaments/edit?id=${tournament.id}">
                                <button class="edit" style="float: right;"><i class="material-icons md-dark">edit</i></button>
                            </a>

                            <c:if test="${empty matches}">
                                <div class = "startbtn">
                                <a href="tournaments/generateMatches?id=${tournament.id}">
                                    <button class="btn btn-primary" id = "start" style="float: right;">Start Tournament</button>
                                </a>
                                </div>
                            </c:if>

                        </c:if>

            </div>
            <c:if test="${allowSignUp}">
                <div>
                    <sf:form action="tournaments/addTeam?id=${tournament.id}" method="POST">
                        <input type="text" name="team" required/>
                        <button class="btn btn-primary">Add Team</button>
                    </sf:form>
                </div>
            </c:if>

            <div class = "tournamentTable">
                <h3> Scoreboard </h3>
                <table>
                    <tr>
                        <th>Team</th>
                        <th>Games Played</th>
                        <th>Goals</th>
                        <th>Goals Against</th>
                        <th>Points</th>
                    </tr>


                    <c:forEach var="team" items="${scoreboard}">
                        <div>
                            <tr>
                                <td>${team.team}</td>
                                <td>${team.gamesPlayed}</td>
                                <td>${team.goalsFor} </td>
                                <td>${team.goalsAgainst} </td>
                                <td>${team.points}</td>
                            </tr>
                        </div>
                    </c:forEach>

                </table>
            </div>

            <c:if test="${not empty matches}">

            <div class = "scoreTable">
                <h3> Games </h3>
                <table>
                    <tr>
                        <th>Round</th>
                        <th>Home</th>
                        <th>Away</th>
                        <th>Score</th>
                        <th>Location</th>
                        <th>Date</th>
                    </tr>


                    <c:forEach var="match" items="${matches}">
                        <div>
                            <tr>
                                <td> ${match.round}</td>
                                <td> ${match.homeTeam.name} </td>
                                <td> ${match.awayTeam.name} </td>

                                <c:choose>
                                    <c:when test="${match.played}">
                                        <td> ${match.homeTeamScore} - ${match.awayTeamScore} </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>  -  </td>
                                    </c:otherwise>
                                </c:choose>

                                <td> ${match.location} </td>
                                <td> <c:if test="${not empty match.matchDate}">${timeFormatter.format(match.matchDate)}</c:if> </td>
                            </tr>
                        </div>
                    </c:forEach>


                </table>
                </c:if>
                <c:if test="${isAuthenticated}">
                <c:if test="${empty matches}">
                   <div class = "notStarted"> <h2> Tournament has not started yet to begin press the "Start Tournament" button </h2>
                   </div>
                </c:if>
                </c:if>

            </div>

        </body>
    </main>
</html>
