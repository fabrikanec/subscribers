<%--
  Created by IntelliJ IDEA.
  User: artemnovikov
  Date: 25.01.2021
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>

<body>

<div>
    <%
        if (request.getAttribute("userName") != null) {
            out.println("<p>User '" + request.getAttribute("userName") + "' added!</p>");
        }
    %>
    <div>
        <div>
            <h2>Add user</h2>
        </div>

        <form method="post">
            <label>First name:
                <input type="text" name="firstName"><br/>
            </label>
            <label>Last name:
                <input type="text" name="lastName"><br/>
            </label>
            <label>Work phone:
                <input type="text" name="workPhone"><br/>
            </label>
            <label>Mobile phone:
                <input type="text" name="mobile"><br/>
            </label>
            <label>Email:
                <input type="email" name="email"><br/>
            </label>



            <button type="submit">Submit</button>
        </form>
    </div>
</div>

</body>
</html>
