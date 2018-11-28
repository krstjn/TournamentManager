<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<head>
    <title>Create tournament</title>
    <meta charset="UTF-8">
    <script src="<c:url value="/js/jquery-3.3.1.min.js" />"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/navigation.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/footer.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/icons.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/grid.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>

</head>
<body>
  <div class="content">
      <%@include file="Navigation.jsp"%>
      <main>
          <div class="center">
              <div style="display: flex; align-items: center; justify-content: center;">
                  <i class="material-icons md-96">error_outline</i><h2>An unexpected error occured</h2>
              </div>
              <c:if test="${not empty errorMsg}">
                  <p>${errorMsg}</p>
              </c:if>

              <a href="/" class="btn btn-primary">Go home</a>
          </div>
      </main>
  </div>
</body>
<%@include file="Footer.jsp"%>
</html>
