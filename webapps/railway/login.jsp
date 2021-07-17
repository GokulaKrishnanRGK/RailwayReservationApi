<html>

<head>
    <title>Railway Reservation</title>
</head>

<body>
    <center>
        <h1>Railway Reservation</h1>
        <form method="post" action="j_security_check">
            <table>
                <tr>
                    <td><label>Name: </label></td>
                    <td><input type="text" name="j_username" /></td>
                </tr>
                <tr>
                    <td><label>Password: </label></td>
                    <td><input type="password" name="j_password" /></td>
                </tr>
            </table>
            <input type="submit" value="Login" /><br>
            <label>New user? <a href="/railway/register.jsp">Register</a></label>
        </form>
        <font color="red">${errorMessage}</font>
    </center>
</body>

</html>