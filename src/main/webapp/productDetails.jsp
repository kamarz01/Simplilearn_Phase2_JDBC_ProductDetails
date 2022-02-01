
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <style>
        .center {
            margin-left: auto;
            margin-right: auto;
        }
    </style>
    <title>Product Details</title>
</head>
<body style="text-align: center">
<table class="center">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach items="${requestScope.products}" var="prod">
        <tr>
            <td>${prod.getId()}</td>
            <td>${prod.getName()}</td>
            <td>${prod.getPrice()}</td>
        </tr>
    </c:forEach>
</table>
<br/>

</body>
</html>
