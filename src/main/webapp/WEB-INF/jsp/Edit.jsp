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
    </head>
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

    <main>
        <body>
            <div class = titleSection>
                <c:forEach var="tournament" items="${tournaments}">
                    <c:if test = "${tournament.id == param.id}" >
                        <h1> ${tournament.name} </h1>
                        <h4> Tegund Móts ${tournament.type} -    Max fjöldi liða ${tournament.maxTeams}
                                -    id ${tournament.id} -   Fjöldi liða í móti   ${tournament.teams.size()} </h4>

                        <c:if test="${isAuthenticated}">
                            <a href="/tournaments?id=${tournament.id}">
                                <button class="edit" style="float: right;"><i class="material-icons">done</i></button>

                            </a>

                        </c:if>

                    </c:if>
                </c:forEach>
            </div>

            <div class = "tournamentTable">
                <h3> Scoreboard </h3>
                <table>
                    <tr>
                        <th>Team</th>
                        <th>Games</th>
                        <th>Won</th>
                        <th>Lost</th>
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


            <div class = "scoreTable">
                <h3> Games </h3>
                <table>
                    <tr>
                        <th>Home</th>
                        <th>Away</th>
                        <th>Score</th>
                        <th>Location</th>
                        <th>Date</th>
                    </tr>

                    <c:forEach var="match" items="${matches}">
                        <div>
                            <tr>
                                <td> ${match.homeTeam.name} </td>
                                <td> ${match.awayTeam.name} </td>
                                <td> <input type = "number">  </input> - <input type = "number">  </input></td>
                                <td> ${match.location} </td>
                                <td> ${match.matchDate} </td>
                            </tr>
                        </div>
                    </c:forEach>

                </table>
            </div>



        </body>
    </main>

</html>
