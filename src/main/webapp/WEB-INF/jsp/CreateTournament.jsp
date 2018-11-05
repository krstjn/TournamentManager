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
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,700|Roboto:400,400i,700" rel="stylesheet">        <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/navigation.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/CreateTournament.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/icons.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/input.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/grid.css"/>"/>
    </head>
    <body>
    <nav class="navbar">
        <div class="navbar--container">
            <div class="navbar--heading">
                <a href="/"><i class="material-icons md-light md-36">home</i></a>
            </div>
            <div class="navbar--nav">
                <a href="/login" class="navbar--item"><i class="material-icons md-light">lock</i>Login</a>
                <a href="/profile" class="navbar--item"><i class="material-icons md-light">face</i></a>
            </div>
        </div>
    </nav>
    <main>
        <div class="container">

            <sf:form method="POST" modelAttribute="tournament" action="/createTournament">
                <div class="row">
                    <div class="col-6">
                        <div class="input-container">
                            <label class="input-label" for="name">Name</label>
                            <sf:input class="input" path="name" type="text"/>
                        </div>
                        <div class="input-container">
                            <label class="input-label" for="maxTeams">Max team limit</label>
                            <sf:input class="input" path="maxTeams" type="number"/>
                        </div>
                        <div class="input-container">
                            <label class="input-label" for="signUpExpiration">SignUp expiration: ${signUpExpiration}</label>
                            <input type="checkbox" name="allowSignUp" id="allowSignUp" value="false">
                            <div id="signUpDate" class="input-group hidden">
                                <sf:input class="input" path="signUpExpiration" type="date" value=""/>
                                <span class="input-icon">
                                    <i id="openSignUpDate" class="far fa-calendar fa-2x"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="input-container">
                            <label class="input-label" for="addTeam">Lið/þáttakendur:</label>
                            <div id="teams__container" name="teams">
                            </div>
                            <div class="input-group" style="display:flex; flex-direction: row;">
                                <input class="input" type="text" id="newTeam"/>
                                <a id="addTeam" class="btn btn-round btn-inline btn-add" style="text-decoration: none">+</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <h3>Tournament setup</h3>
                    <div class="col-6">
                        <div class="">
                            <div>
                                <sf:radiobutton path="type" id="GroupStage" value="GroupStage"/>
                                <label for="GroupStage">Group stage</label>
                            </div>
                            <div>
                                <sf:radiobutton path="type" id="Knockout" value="Knockout"/>
                                <label for="Knockout">Knockout</label>
                            </div>
                            <div>
                                <sf:radiobutton path="type" id="League" value="League"/>
                                <label for="League">League</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="">
                            <label for="isPublic">Public: </label>
                            <sf:checkbox path="isPublic" value="true" />
                        </div>
                    </div>
                </div>


                <button id="submitTournament" class="hidden"  type="submit">Búa til mót</button>
                <input type="checkbox" name="allowSignUp" class="hidden">
            </sf:form>
            <button id="verifyTournament" class="btn btn-primary">Búa til mót</button>
        </div>

        <a href="/">Forsíða</a>
    </main>
    </body>
    <footer>Class HBV501G, University of Iceland</footer>
    <script>
        $('document').ready(function (){

            var dtToday = new Date();
            var month = dtToday.getMonth() + 1;
            var day = dtToday.getDate();
            var year = dtToday.getFullYear();
            if(month < 10)
                month = '0' + month.toString();
            if(day < 10)
                day = '0' + day.toString();

            var minDate = year + '-' + month + '-' + day;
            $('#signUpExpiration').attr('min', minDate);

            $('#addTeam').click(function (e) {
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

            $('#allowSignUp').change(function (){
                if($('#allowSignUp').is(':checked')){
                    $('#allowSignUp').val('true');
                    $('#signUpDate').removeClass('hidden');
                }
                else {
                    $('#allowSignUp').val('false');
                    $('#signUpDate').addClass('hidden');
                }
            });

        });
    </script>
</html>
