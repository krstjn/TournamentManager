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
    </head>
    <body>
    <script>
        $('document').ready(function (){
            var $addTeam = $('#addTeam');

            $addTeam.click(function (e) {
                var team = $('#newTeam').val();
                if(team.length === 0 || team === undefined) return;
                console.log(team);
                var p = $("<p name='teamList' id='teamList'></p>").text(team);
                var hidden = $("<input name='teamList' id='teamList' style='display: none' />").val(team);
                $('#teams__container').append(p);
                $('#teams__container').append(hidden);
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
    <sf:form method="POST" modelAttribute="tournament" action="/createTournament">
    <div>
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
        <div>
            <p>Lið/þáttakendur:</p>
            <div id="teams__container" name="teams">
            </div>
            <span id="addTeam" class="button">Bæta við liði:</span>
            <input type="text" id="newTeam"/>
        </div>
    </div>

    <button id="submitTournament" class="button" style="display:none" type="submit">Búa til mót</button>
    </sf:form>
    <button id="verifyTournament" class="button">Búa til mót</button>

    <a href="/">Forsíða</a>
    </body>
    <footer>Class HBV501G, University of Iceland</footer>
</html>
