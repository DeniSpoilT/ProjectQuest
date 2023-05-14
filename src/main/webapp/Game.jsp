<%@ page import="com.komarov.projectnlo.model.Step" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="/style.css">
    <title>${state}</title>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
        $(document).ready(function () {
            if ("${state}" == "CAPTAIN") {
                $('input[type="radio"]').hide();
                $('#button').hide();
            }
        });
    </script>
</head>
<body>

<p>${question}</p>

<% boolean answer = (boolean)request.getAttribute("answer"); %>

   <% if (answer) { %>
<form action="game" method="post">
    <input type="radio" name="answer" value="true">${answer1}<br>
    <input type="radio" name="answer" value="false">${answer2}<br>
    <input type="submit" id="button" value="Ответить">
</form>
<% } else { %>
<form action="game" method="get">
    <input type="hidden" name="restart" value="true">
    <input type="submit" id="tryAgain" value="Попробуй еще раз">
</form>
<% } %>

<jsp:include page="/WEB-INF/sessionView.jsp"/>
</body>
</html>
