<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Country List</title>
    <%@ include file="/WEB-INF/views/sidebar.jsp" %>
    <style>
        .content {
            margin-left: 260px;
            padding: 20px;
        }
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 20px;
        }
        h1 {
            color: #333;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #3f51b5;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>

<h1 style="text-align: center;">List of Countries</h1>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Country Name</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="country" items="${countries}">
        <tr>
            <td>${country.id}</td>
            <td>${country.countryCode}</td>
            <td>${country.countryName}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>
