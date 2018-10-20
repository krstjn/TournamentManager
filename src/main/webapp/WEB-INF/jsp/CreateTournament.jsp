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
        <script src="<c:url value="/js/jquery-3.3.1.min.js" />"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/navigation.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/CreateTournament.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>
    </head>
    <body>
    <div class="navbar">
        <div class="navbar--container">
            <div class="navbar--heading">
                <a href="/"><img class="svg" src="<c:url value="/img/home-48.svg"/>" alt="Forsíða"></a>
            </div>
            <div class="navbar--nav">
                <a href="/" class="navbar--item"><img class="svg" src="<c:url value="/img/user-30.svg"/>" alt="Menu"></a>
                <a href="/" class="navbar--item"><img class="svg" src="<c:url value="/img/calendar-30.svg"/>" alt="Cal"></a>
                <a href="/" class="navbar--item"><img class="svg"
                                                      src="<c:url value="/img/hamburger-30.svg"/>" alt="Menu"></a>
            </div>
        </div>
    </div>
    <main>
        <div class="form">

            <sf:form method="POST" modelAttribute="tournament" action="/createTournament">
                <div class="form-row">
                    <div class="form-col form-col-6">
                        <div>
                            <label for="name"></label>
                            <sf:input path="name" type="text" placeholder="Nafn móts"/>
                        </div>
                        <div>
                            <label for="maxTeams"></label>
                            <sf:input path="maxTeams" type="number"/>
                        </div>
                        <div>
                            <label for="signUpExpiration">Skráningarfrestur:</label>
                            <sf:input path="signUpExpiration" type="date"/>
                        </div>
                    </div>
                    <div class="form-col form-col-6">
                        <div>
                            <p>Lið/þáttakendur:</p>
                            <div id="teams__container" name="teams">
                            </div>
                            <div style="display:flex; flex-direction: row;">
                                <input type="text" id="newTeam"/>
                                <div id="addTeam" class="button button-round">+</div>
                            </div>
                        </div>
                    </div>
                </div>


                <button id="submitTournament" class="button hidden"  type="submit">Búa til mót</button>
            </sf:form>
            <button id="verifyTournament" class="button">Búa til mót</button>
        </div>

        <a href="/">Forsíða</a>
    </main>
    </body>
    <footer>Class HBV501G, University of Iceland</footer>
    <script>
        $('document').ready(function (){
            var $addTeam = $('#addTeam');

            $addTeam.click(function (e) {
                var team = $('#newTeam').val();
                var $teamContainer = $('#teams__container');
                if(team.length === 0 || team === undefined) return;
                console.log(team);
                var p = $("<p name='myTeams' id='myTeams'></p>").text(team);
                var hidden = $("<input name='myTeams' id='myTeams' style='display: none' />").val(team);
                $teamContainer.append(p, hidden);
                $('#newTeam').val("");
            });

            $('#verifyTournament').click(function (e) {
                var name = $('#name').val();
                if(name !== undefined && name !== ''){
                    $('#submitTournament').click();
                }
            });
        });
    </script>
</html>
