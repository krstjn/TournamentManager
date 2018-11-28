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
                            <label class="input-label" for="name">Tournament name</label>
                            <sf:input class="input" path="name" type="text"/>
                        </div>
                        <div class="input-container">
                            <label class="input-label" for="maxTeams">Maximum team limit</label>
                            <sf:input class="input" path="maxTeams" type="number"/>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="input-container">
                            <label class="input-label" for="addTeam">Teams</label>
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
                    <div class="col-3">
                        <div class="input-container">
                            <label class="input-label" for="signUpDate">Allow sign up: <input type="checkbox" name="allowSignUp" id="allowSignUp" value="false"></label>
                            <div id="signUpDate" class="input-group hidden">
                                <label for="signUpExp-date">Sign up expiration: </label>
                                <input class="input" name="signUpExp" id="signUpExp-date" type="datetime-local" min="${minDate}"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="input-container flex-row">
                            <label class="input-label" for="nrOfRounds">Number of rounds:</label>
                            <sf:select class="input col-4" path="nrOfRounds">
                                <sf:option value="1">1</sf:option>
                                <sf:option value="2">2</sf:option>
                                <sf:option value="3">3</sf:option>
                                <sf:option value="4">4</sf:option>
                            </sf:select>
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="">
                            <label for="isPublic">Public: </label>
                            <sf:checkbox path="isPublic" value="true" />
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="input-container">
                            <label class="input-label" for="sport">Sport</label>
                            <sf:select class="input" path="sport">
                                <sf:option value="Football">Football</sf:option>
                                <sf:option value="Handball">Handball</sf:option>
                                <sf:option value="Basketball">Basketball</sf:option>
                            </sf:select>
                        </div>
                    </div>
                </div>


                <button id="submitTournament" class="hidden"  type="submit"></button>
            </sf:form>
            <button id="verifyTournament" class="btn btn-primary">Búa til mót</button>
        </div>
    </main>
    </div>
    </body>
    <%@include file="Footer.jsp"%>

    <script>
        function removeTeam(element) {
            var container = element.parentNode;
            container.parentNode.removeChild(container);
        };

        $('document').ready(function (){



            $('#addTeam').click(function (e) {
                if(parseInt($('#maxTeams').val()) <= $('#teams__container div').length) return;
                var team = $('#newTeam').val();
                if(team.length === 0 || team === undefined) return;

                var $teamContainer = $('#teams__container');
                var container = $("<div class='input-group flex-row-reverse'></div>");
                container.attr('id', team);

                var p = $("<p name='myTeams' id='myTeams' style='width: 100%'></p>").text(team);
                var button = $("<i id='removeTeam' class='material-icons md-18 btn btn-remove' onclick='removeTeam(this)'>remove</i>").attr('name',team);
                var hidden = $("<input name='myTeams' id='myTeams' class='hidden' />").val(team);
                container.append(p, button, hidden);
                $teamContainer.append(container);
                $('#newTeam').val("");
            });

            $('#verifyTournament').click(function (e) {
                var name = $('#name').val();
                if(name !== undefined && name !== ''){
                    $('#submitTournament').click();
                } else {
                    $('#name').addClass('input-danger');
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

            $('#name').change(function () {
                $('#name').removeClass('input-danger');

            });

                $(window).keydown(function(e){
                if(e.keyCode === 13) {
                    e.preventDefault();
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
