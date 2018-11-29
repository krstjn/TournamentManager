<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">

    <head>
        <title>Tournament View</title>
        <meta charset="UTF-8">
        <script src="<c:url value="/js/jquery-3.3.1.min.js" />"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700|Roboto:400,400i,700" rel="stylesheet">        <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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

                <h1> ${tournament.name} </h1>
                <h4> Max fjöldi liða ${tournament.maxTeams}  -   Fjöldi liða í móti  ${tournament.teams.size()} - Fjöldi umferða ${tournament.nrOfRounds} </h4>


                <sf:form method="POST" modelAttribute="tournament" action="/tournaments/edit?id=${tournament.id}">
                <sf:hidden path = "user" />
                <sf:hidden path = "maxTeams" />
                <sf:hidden path = "name"/>


                        <c:if test="${isAuthenticated}">
                            <a href="/tournaments?id=${tournament.id}">
                                <button id ="submitScore" class="edit" type = "submit" style="float: right;"><i class="material-icons md-dark">done</i></button>
                            </a>
                        </c:if>

             </sf:form>
            </div>

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

            <div id="matches" class = "scoreTable">
                <h3> Games </h3>
                <table>
                    <tr>
                        <th>Round</th>
                        <th>Home</th>
                        <th>Away</th>
                        <th>Score</th>
                        <th>Location</th>
                        <th>Date</th>
                        <th></th>
                    </tr>

                    <c:forEach var="match" items="${matches}">
                        <div>
                            <tr>
                                <sf:form method="POST" action="/tournaments/editMatch?id=${match.id}">
                                    <td> ${match.round}</td>
                                    <td> ${match.homeTeam.name} </td>
                                    <td> ${match.awayTeam.name} </td>
                                    <td> <input class="input" name="homeScore" type = "number" value="${match.homeTeamScore}" min = "0"/> - <input class = "input" name="awayScore" value="${match.awayTeamScore}" type = "number" min="0"/></td>
                                    <td> <input class="input" name="location" type="text" style="width: 90%" placeholder="${match.location}"/> </td>
                                    <td> <input class="input" name="matchDate" type="datetime-local" value="${match.matchDate}" style="width: 95%" min="${minDate}"/> </td>
                                    <td> Played:
                                        <c:if test="${match.played}">
                                            <input class="input" name="played" type="checkbox" checked disabled>
                                        </c:if>
                                        <c:if test="${!match.played}">
                                            <input class="input" name="played" type="checkbox">
                                        </c:if>
                                        <button class="btn btn-primary">Submit</button>
                                    </td>
                                </sf:form>

                            </tr>
                        </div>
                    </c:forEach>

                </table>
            </div>
        </body>
    </main>
</html>

