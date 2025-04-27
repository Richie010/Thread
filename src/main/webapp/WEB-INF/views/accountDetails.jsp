<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Account Details</title></head>
<body>
<h2>Account List</h2>
<table border="1">
    <tr>
        <th>Account No</th>
        <th>Name</th>
        <th>CIF ID</th>
        <th>Balance</th>
        <th>Working Balance</th>
        <th>Branch</th>
    </tr>
    <c:forEach var="acc" items="${accounts}">
        <tr>
            <td>${acc.accNo}</td>
            <td>${acc.accName}</td>
            <td>${acc.cifId}</td>
            <td>${acc.balance}</td>
            <td>${acc.workingBalance}</td>
            <td>${acc.branchName}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
