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
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/footer.css"/>"/>
    </head>
    <body>
    <div class="content">
        <%@include file="Navigation.jsp"%>
        <main>
        <div class="container">

            <sf:form method="POST" modelAttribute="tournament" action="/tournaments/create">
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
                            <label class="input-label" for="signUpDate">SignUp expiration: <input type="checkbox" name="allowSignUp" id="allowSignUp" value="false">
                            </label>
                            <div id="signUpDate" class="input-group hidden">
                                <input class="input" name="signUpExp" id="signUpExp-date" type="datetime-local" min="${minDate}"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="input-container">
                            <label class="input-label" for="addTeam">Lið/þáttakendur:</label>
                            <div id="teams__container" name="teams">
                            </div>
                            <div class="input-group flex-row-reverse">
                                <input class="input" type="text" id="newTeam" autocomplete="off"/>
                                <i id="addTeam" class="material-icons md-18 btn btn-add">add</i>
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


                <button id="submitTournament" class="hidden"  type="submit"></button>
            </sf:form>
            <button id="verifyTournament" class="btn btn-primary">Búa til mót</button>
        </div>

        <a href="/">Forsíða</a>
    </main>
    </div>
    </body>
    <%@include file="Footer.jsp"%>

    <script>
        $('document').ready(function (){

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
                    $('#signUpExp-date').val(null);
                }
            });

            $('#signUpExp-date').change(function (){
                console.log($('#signUpExp-date').val());
            });

            $(window).keydown(function(e){
                if(e.keyCode === 13) {
                    e.preventDefault();
                    console.log(e)
                    var target = e.target.id || e.target.name;
                    if(target === 'newTeam'){
                        $('#addTeam').click();
                    }
                    else{
                        $('#verifyTournament').click();
                    }
                    return false;
                }

            });


        });
    </script>
</html>
