<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>All Holidays</title>
    <%@ include file="/WEB-INF/views/sidebar.jsp" %>

    <style>
        .content {
            margin-left: 20px;
            padding: 20px;
        }
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 20px;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        .top-bar {
            display: flex;
            justify-content: flex-end;
            margin: 0 10% 20px;
        }
        .top-bar button {
            padding: 10px 15px;
            background-color: #3f51b5;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .top-bar button:hover {
            background-color: #2c387e;
        }
        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
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

<h1>All Holidays</h1>

<div class="top-bar">
    <form action="addHoliday" method="get">
        <button type="submit">+ Add New Holiday</button>
    </form>
</div>

<table>
    <thead>
    <tr>
       <%-- <th>ID</th>--%>
        <th>Holiday Date</th>
        <th>Description</th>
        <th>Country</th>
      <%--  <th>Pay Method</th>--%>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="holiday" items="${holidays}">
        <tr>

            <td>${holiday.holidayDate}</td>
            <td>${holiday.description}</td>
            <td>${holiday.countryName}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
