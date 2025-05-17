<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sidebar with Dropdown</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            display: flex;
        }
        .sidebar {
            width: 250px;
            height: 100vh;
            background: #2f4050;
            color: white;
            padding: 0;
        }
        .sidebar ul {
            list-style: none;
            padding-left: 0;
            margin: 0;
        }
        .sidebar li {
            padding: 15px 20px;
            cursor: pointer;
            border-bottom: 1px solid #3a4a61;
        }
        .sidebar li:hover {
            background: #1a2736;
        }
        .sidebar li a {
            color: white;
            text-decoration: none;
            display: block;
            width: 100%;
        }
        .submenu {
            background: #394d69;
            display: none;
        }
        .submenu li {
            padding-left: 40px;
            border-bottom: none;
        }
        .submenu li:hover {
            background: #1a2736;
        }
        .submenu.show {
            display: block;
        }
        .arrow {
            float: right;
            transition: transform 0.3s ease;
        }
        .arrow.down {
            transform: rotate(0deg);
        }
        .arrow.up {
            transform: rotate(180deg);
        }
        .content {
            flex: 1;
            padding: 20px;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <ul>
        <li id="holidayManagementMenu">
            Holiday Management
            <span class="arrow down">&#9660;</span>
        </li>
        <ul class="submenu" id="holidaySubmenu">
            <li><a href="${pageContext.request.contextPath}/viewHolidays">Add Holiday</a></li>
            <li><a href="${pageContext.request.contextPath}/viewHolidays">Summary</a></li>
            <li><a href="${pageContext.request.contextPath}/viewHolidays">Approve</a></li>
        </ul>

    </ul>
</div>

<div class="content">
    <h1>Welcome to the Dashboard</h1>
    <p>This is the main content area.</p>
</div>

<script>
    const holidayMenu = document.getElementById('holidayManagementMenu');
    const holidaySubmenu = document.getElementById('holidaySubmenu');
    const arrow = holidayMenu.querySelector('.arrow');

    holidayMenu.addEventListener('click', function() {
        if (holidaySubmenu.classList.contains('show')) {
            holidaySubmenu.classList.remove('show');
            arrow.classList.remove('up');
            arrow.classList.add('down');
        } else {
            holidaySubmenu.classList.add('show');
            arrow.classList.remove('down');
            arrow.classList.add('up');
        }
    });
</script>

</body>
</html>
