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
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/navigation.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/TournamentView.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/icons.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/input.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/grid.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/footer.css"/>"/>
    </head>
    <body>
    <div class="content">
    <nav class="navbar">
        <div class="navbar--container">
            <div class="navbar--heading">
            <a href="/"><i class="material-icons md-light md-36">home</i></a>
            </div>
        <div class="navbar--nav">
            <a href="/" class="navbar--item"><i class="material-icons md-light">calendar_today</i></a>
            <a href="/" class="navbar--item"><i class="material-icons md-light">menu</i></a>
            <a href="/" class="navbar--item"><i class="material-icons md-light">face</i></a>
            <a href="/" class="navbar--item"><i class="far fa-inverse fa-2x fa-user"></i></a>
            <a href="/" class="navbar--item"><i class="far fa-inverse fa-2x fa-calendar"></i></a>
            <a href="/" class="navbar--item"><i class="far fa-inverse fa-2x fa-comment"></i></a>
            </div>
        </div>
    </nav>
        <main>
            <div class = titleSection>
                <c:forEach var="tournament" items="${tournaments}">
                    <c:if test = "${tournament.id == param.id}" >
                        <h1> ${tournament.name} </h1>
                        <h4> Tegund Móts ${tournament.type} -    Max fjöldi liða ${tournament.maxTeams}
                                -    id ${tournament.id} -   Fjöldi liða í móti   ${tournament.teams.size()} </h4>

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



                    <c:forEach var="tournament" items="${tournaments}">
                        <c:if test = "${tournament.id == param.id}" >

                            <c:forEach var="team" items="${tournament.teams}">
                                <div>
                                    <tr>
                                        <td>${team.name}</td>
                                        <td>${team.id}</td>
                                        <td> Ehv </td>
                                        <td> Ehv </td>
                                        <td> Ehv </td>
                                    </tr>
                                </div>
                            </c:forEach>
                        </c:if>
                    </c:forEach>

                </table>
            </div>


            <div class = "scoreTable">
                <h3> Leikir </h3>
                <table>
                    <tr>
                        <th>Home</th>
                        <th>Away</th>
                        <th>Score</th>
                        <th>Location</th>
                        <th>Date</th>
                    </tr>



                    <c:forEach var="tournament" items="${tournaments}">
                        <c:if test = "${tournament.id == param.id}" >

                            <c:forEach var="team" items="${tournament.teams}">
                                <div>
                                    <tr>
                                        <td>${team.name}</td>
                                        <td>${team.id}</td>
                                        <td> Ehv </td>
                                        <td> Ehv </td>
                                        <td> Ehv </td>
                                    </tr>
                                </div>
                            </c:forEach>
                        </c:if>
                    </c:forEach>

                </table>
            </div>

            <%--    á eftir að implementa fyrir set<mathces>

                    <c:forEach var="tournament" items="${tournaments}">
                        <c:if test = "${tournament.id == param.id}" >

                            <c:forEach var="match" items="${tournament.matches}">
                                <div>
                                    <tr>
                                        <td>${match.homeTeam}</td>
                                        <td>${match.awayTeam}</td>
                                        <td>${match.location}</td>
                                        <td>${match.awayTeam}</td>
                                        <td>${match.matchDate}</td>
                                    </tr>
                                </div>
                            </c:forEach>

                        </c:if>
                    </c:forEach>

                </table>
            </div>

 --%>

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
