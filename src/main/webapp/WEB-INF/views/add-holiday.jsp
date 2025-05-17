<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Holiday</title>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <%@ include file="/WEB-INF/views/sidebar.jsp" %>
    <style>
        .content {
            margin-left: 260px; /* sidebar width + some padding */
            padding: 20px;
        }
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            width: 60%;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[readonly] {
            background-color: #e9ecef;
        }
        button {
            margin-top: 20px;
            padding: 12px 20px;
            background-color: #3f51b5;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #2c387e;
        }
    </style>
</head>
<body>

<h1>Add New Holiday</h1>

<form action="${pageContext.request.contextPath}/addHoliday" method="post">
    <label for="countryId">Select Country ID</label>
    <select id="countryId" name="countryId" required>
        <option value="">-- Select Country --</option>
        <c:forEach var="country" items="${countries}">
            <option value="${country.id}">${country.id} - ${country.countryName}</option>
        </c:forEach>
    </select>

    <label>Country Code</label>
    <input type="text" id="countryCode" readonly />

    <label for="payMethodId">Select Pay Method</label>
    <select id="payMethodId" name="payMethodId" required>
        <option value="">-- Select Pay Method --</option>
    </select>

    <label for="holidayDate">Holiday Date</label>
    <input type="date" id="holidayDate" name="holidayDate" required />

    <label for="description">Description</label>
    <input type="text" id="description" name="description" required />

    <button type="submit">Submit Holiday</button>
</form>

<script>
    const contextPath = '${pageContext.request.contextPath}';

    $(document).ready(function () {
        $("#countryId").change(function () {
            const countryId = $(this).val();

            if (countryId) {
                $.ajax({
                    url: contextPath + '/country/code/' + countryId,
                    type: 'GET',
                    success: function (data) {
                        console.log("Country code:", data);
                        $("#countryCode").val(data);
                    },
                    error: function (xhr) {
                        alert("Failed to load country code: " + xhr.statusText);
                    }
                });

                $.ajax({
                    url: contextPath + '/paymethods/' + countryId,
                    type: 'GET',
                    success: function (data) {
                        let payMethodSelect = $("#payMethodId");
                        payMethodSelect.empty();
                        payMethodSelect.append('<option value="">-- Select Pay Method --</option>');
                        $.each(data, function (i, method) {
                            payMethodSelect.append('<option value="' + method.id + '">' + method.paymethodCode + '</option>');
                        });
                    },
                    error: function (xhr) {
                        alert("Failed to load pay methods: " + xhr.statusText);
                    }
                });
            } else {
                $("#countryCode").val('');
                $("#payMethodId").empty().append('<option value="">-- Select Pay Method --</option>');
            }
        });
    });
</script>

</body>
</html>
