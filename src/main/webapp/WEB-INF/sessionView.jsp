<%--
  Created by IntelliJ IDEA.
  User: AMD-64x
  Date: 30.04.2023
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
</head>
<body>
<table>
    <colgroup>
        <col width="150">
    </colgroup>
    <tr class="session">
        <td> <b>Session</b> </td>
    </tr>
    <tr class="other">
        <td>${sessionScope.get('name')}</td>
    </tr>
    <tr class="other">
        <td><%= new java.util.Date() %></td>
    </tr>
</table>
</body>
</html>
