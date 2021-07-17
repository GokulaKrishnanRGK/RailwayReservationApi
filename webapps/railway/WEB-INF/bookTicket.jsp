<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
            <a href="/railway/user/dashboard">Booking Details</a>
            <a href="/railway/user/cancel">Cancel Ticket</a>
            <a class="active">Book Ticket</a>
        </div>
        <center>
            <h1>Railway Reservation</h1><br>
            <!-- <label id="welcomeLabel" value='<%= session.getAttribute("name") %>'></label> -->
            <label id="welcomeLabel">Welcome!</label>
            <h2>Book Tickets</h2>
            <table>
                <tr>
                    <td><label>From</label></td>
                    <td><input type="text" id="from" placeholder="From" /></td>
                    <td><label>To</label></td>
                    <td><input type="text" id="to" placeholder="To" /></td>
                </tr>
            </table><br>
            <div id="ticketsListDiv">
                <table border="1" cellpadding="10">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Class Name</th>
                            <th>Available</th>
                            <th>Waiting</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ticket" items="${tickets}">
                            <tr>
                                <td><input type='radio' name='classChoice' value=${ticket.classId} onclick="addTable()" /></td>
                                <td>${ticket.className}</td>
                                <td>${ticket.available}</td>
                                <td>${ticket.waiting}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <br>
            <div id="passengerDiv"></div>
            <input type="button" onclick="bookTickets()" value="Book Tickets" /><br>
            <label id="errorMessage"></label>
        </center>
    </body>
    <script>
        var isClicked = false;

        function bookTickets() {
            var from = document.getElementById("from").value;
            var to = document.getElementById("to").value;
            if (from == "" || to == "" || from == to) {
                document.getElementById("errorMessage").innerHTML = "From and To should not be null or the same <br><br>";
                document.getElementById("errorMessage").setAttribute("style", "color:red");
                return;
            }
            var classId = $("input[name=classChoice]:checked").val();
            var json = {};
            json["from"] = from;
            json["to"] = to;
            json["classId"] = classId;
            var otArr = [];
            var isValid = true;
            var tbl2 = $('#passengerTable tr').each(function(i) {
                if (i > 0) {
                    x = $(this).children();
                    var itArr = [];
                    var name = x.children().eq(0).val();
                    var age = x.children().eq(1).val();
                    var gender = x.children().eq(2).val();
                    console.log(name);
                    console.log(age);
                    console.log(gender);
                    if (name == "" || age == "" || gender == "select") {
                        document.getElementById("errorMessage").innerHTML = "Invalid Passenger details <br><br>";
                        document.getElementById("errorMessage").setAttribute("style", "color:red");
                        isValid = false;
                        return;
                    }
                    itArr.push(name)
                    itArr.push(age)
                    itArr.push(gender)
                    otArr.push(itArr);
                }
            })
            if (!isValid) {
                return;
            }
            if (otArr.length == 0) {
                document.getElementById("errorMessage").innerHTML = "Add atleast 1 passenger <br><br>";
                document.getElementById("errorMessage").setAttribute("style", "color:red");
                return;
            }
            json["tickets"] = otArr;
            console.log(json);
            document.getElementById("errorMessage").innerHTML = "Processing... <br><br>";
            document.getElementById("errorMessage").setAttribute("style", "color:black");
            $.ajax({
                url: '/railway/user/tickets',
                type: 'post',
                dataType: 'json',
                contentType: 'application/json',
                async: false,
                success: function(data) {
                    if (data == "1") {
                        document.getElementById("errorMessage").innerHTML = "Tickets Booked Successfully! Ticket Details Mailed!<br><br>";
                        document.getElementById("errorMessage").setAttribute("style", "color:green");
                        setTimeout(function() {
                            window.location.href = 'tickets';
                        }, 3000);
                    }
                },
                error: function(data) {
                    document.getElementById("errorMessage").innerHTML = "Booking unsuccessfully! <br><br>";
                    document.getElementById("errorMessage").setAttribute("style", "color:red");
                },
                data: JSON.stringify(json)
            });
        }

        function addTable() {
            if (!isClicked) {
                var passengerDiv = $("#passengerDiv");
                var table = $('<table cellpadding="10" id="passengerTable"><tr><th></th><th>Name</th><th>Age</th><th>Gender</th><th><input type="button" onclick="addPassenger()" value="Add Passenger" /><br></th></tr></table>');
                passengerDiv.append(table);
                addPassenger();
                isClicked = true;
            }
        }

        function addPassenger() {
            var passengerTable = $("#passengerTable");
            var row = $('<tr><td></td><td><input type="text" placeholder="Name" name="name"/></td> <td><input type="number" placeholder="Age" name="age"/> </td> <td> </td> <td></td></tr>');
            var gender = $('<select name="gender"><option value="select">Select</option><option value="Male">Male</option><option value="Female">Female</option><option value="Other">Other</option></select>');
            var remove = $('<input type="button" class="remove" onclick="removePassenger(this)" value="Remove"/>')
            row.children().eq(3).append(gender);
            row.children().eq(4).append(remove);
            row.appendTo(passengerTable);
        }

        function removePassenger(btn) {
            console.log("Here")
            $(btn).closest('tr').remove();
        }
    </script>

    </html>