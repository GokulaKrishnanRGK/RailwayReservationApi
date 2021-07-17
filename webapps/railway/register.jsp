<html>

<head>
    <title>Railway Reservation</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
    <center>
        <h1>Railway Reservation</h1>
        <h1>Registration Page</h1>
        <form id="registerForm" method="post">
            <table>
                <tr>
                    <td><label>Email Id: </label></td>
                    <td><input type="email" name="userid" id="email" required /></td>
                </tr>
                <tr>
                    <td><label>Name: </label></td>
                    <td><input type="text" name="name" id="name" required /></td>
                </tr>
                <tr>
                    <td><label>Phone No.: </label></td>
                    <td><input type="phoneno" name="phoneno" id="phoneno" pattern="[0-9]{10}" required /></td>
                </tr>
                <tr>
                    <td><label>DOB: </label></td>
                    <td><input type="date" name="date" id="dob" required /></td>
                </tr>
                <tr>
                    <td><label>Password: </label></td>
                    <td><input type="password" name="password" id="password" required /></td>
                </tr>
                <tr>
                    <td><label>Retype Password: </label></td>
                    <td><input type="password" id="repassword" required /><br></td>
                </tr>
            </table>
            <input type="submit" value="Submit"><br>
            <input type="button" onclick="location.href='/railway/logout.jsp';" value="Go Back" />
            <label id="errorMessage"></label>
        </form>
    </center>
</body>
<script>
    const form = document.querySelector('#registerForm');
    var date = new Date();
    date.setDate(date.getDate() - 1);
    var today = date.toISOString().split('T')[0];
    document.getElementById("dob").setAttribute('max', today);

    form.addEventListener('submit', (event) => {
        console.log("here");
        // disable default action
        event.preventDefault();
        var userid = document.getElementById("registerForm").elements["userid"].value;
        var name = document.getElementById("registerForm").elements["name"].value;
        var phoneno = document.getElementById("registerForm").elements["phoneno"].value;
        var dob = document.getElementById("registerForm").elements["date"].value;
        var password = document.getElementById("registerForm").elements["password"].value;
        var repassword = document.getElementById("registerForm").elements["repassword"].value;
        if (password != repassword) {
            document.getElementById("errorMessage").innerHTML = "Passwords must match!";
            document.getElementById("errorMessage").setAttribute("style", "color:red");
            return;
        }
        const json = {
            "userid": userid,
            "name": name,
            "phoneno": phoneno,
            "dob": dob,
            "password": password
        };
        var result = registerUser(json);

        if (result == "1") {
            document.getElementById("errorMessage").innerHTML = "Registration Successful! <br> Redirecting to login...<br>";
            document.getElementById("errorMessage").setAttribute("style", "color:green");
            setTimeout(function() {
                window.location.href = '/railway/logout.jsp';
            }, 3000);
        } else if (result == "0") {
            document.getElementById("errorMessage").innerHTML = "User Exists!";
            document.getElementById("errorMessage").setAttribute("style", "color:red");
        } else if (result == "-1") {
            document.getElementById("errorMessage").innerHTML = "Error occured!";
            document.getElementById("errorMessage").setAttribute("style", "color:red");
        }
    });

    function registerUser(json) {
        var result;
        $.ajax({
            url: '/railway/register',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            success: function(data) {
                result = data;
            },
            data: JSON.stringify(json)
        });
        return result;
    }
</script>

</html>