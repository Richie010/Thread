<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cheque Report Download</title>
</head>
<body>
<h2>Download Cheque Reports</h2>

<!-- Form that posts to your Spring controller -->
<form id="chequeReportForm" method="post" action="${pageContext.request.contextPath}/downloadChequeReportXLS" target="_blank">
    <input type="hidden" name="accNO" id="accNO" />

    <ul>
        <li><a href="#" onclick="submitForm('ACC123456')">ACC123456</a></li>
        <li><a href="#" onclick="submitForm('ACC987654')">ACC987654</a></li>
        <li><a href="#" onclick="submitForm('ACC543210')">ACC543210</a></li>
    </ul>
</form>

<script>
    function submitForm(accNo) {
        document.getElementById('accNO').value = accNo;
        document.getElementById('chequeReportForm').submit();
    }
</script>
</body>
</html>
