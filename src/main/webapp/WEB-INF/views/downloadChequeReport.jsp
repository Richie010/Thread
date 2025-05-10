<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Download Cheque Report</title>
</head>
<body>
<h2>Download Cheque Report ZIP</h2>

<form action="${pageContext.request.contextPath}/downloadChequeReportXLS" method="post">
    <label for="accNO">Enter Account Number:</label>
    <input type="text" id="accNO" name="accNO" required />
    <br><br>
    <button type="submit">Download ZIP</button>
</form>
</body>
</html>
