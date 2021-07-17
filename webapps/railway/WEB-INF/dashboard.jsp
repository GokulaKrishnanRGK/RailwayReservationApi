<html>

<head>
    <title>Railway Reservation</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <style>
        .topnav {
            overflow: hidden;
            background-color: #333;
        }
        
        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
            float: right;
        }
        
        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }
        
        .topnav a.active {
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>

<body>
    <div class="topnav">
        <a href="/railway/logout.jsp">Logout</a>
        <a class="active">Booking Details</a>
        <a href="/railway/user/cancel">Cancel Ticket</a>
        <a href="/railway/user/tickets">Book Ticket</a>
    </div>
    <center>
        <h1>Railway Reservation</h1>
        <h2>Booking Details</h2>
        <label id="welcomeLabel"></label>
        <label id="noBookingLabel"></label>
        <div id="bookingListDiv"></div>
        <br>
        <div id="sendMail"></div>
        <!-- <input type="button" onclick="location.href='/railway/tickets';" value="Back" />
        <input type="button" onclick="location.href='/railway/logout';" value="Logout" /> -->
    </center>
</body>
<script>
    $(document).ready(function() {
        $.ajax({
            url: '/railway/user/dashboard',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            success: function(data) {
                fillTable(data);
            }
        });
    });

    function fillTable(data) {
        // var name = '<%= session.getAttribute("name") %>';
        // $("#welcomeLabel").append("Welcome " + name + "!");
        $("#welcomeLabel").append("Welcome!")
        if (!$.isArray(data) || !data.length) {
            $("#noBookingLabel").append("No bookings yet!");
        } else {
            var bookingTable = $("#bookingListDiv");;
            var table = $('<table border="1 " cellpadding="10 "><tr><th>Booking Id</th><th>Customer Id</th><th>Name</th><th>Age</th><th>Gender</th><th>From</th><th>To</th><th>Class Name</th><th>SeatNo</th><th>Status</th></tr></table>');
            $.each(data, function(key, value) {
                var row = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
                row.children().eq(0).text(value['bookingId']);
                row.children().eq(1).text(value['customerId']);
                row.children().eq(2).text(value['name']);
                row.children().eq(3).text(value['age']);
                row.children().eq(4).text(value['gender']);
                row.children().eq(5).text(value['source']);
                row.children().eq(6).text(value['destination']);
                row.children().eq(7).text(value['className']);
                row.children().eq(8).text(value['seatNo']);
                row.children().eq(9).text(value['status']);
                row.appendTo(table);
            });
            bookingTable.append(table);
            var addPassengerBtn = $('<input id="sendMailButton" type="button" onclick="sendMailNotification()" value="Send Booking to Mail"><br>');
            $("#sendMail").append(addPassengerBtn);
        }
    }

    function sendMailNotification() {
        $.ajax({
            url: '/railway/user/sendMail',
            type: 'get',
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            success: function(data) {
                $("#sendMail").append($('<label style="color:green">Booking details sent to mail successfully!</label><br>'));
                $("#sendMailButton").prop('disabled', true);
            },
            error: function(data) {
                $("#sendMail").append($('<label style="color:red">Sending Mail unsuccessfully!</label><br>'));
            }
        });
    }
</script>

</html>