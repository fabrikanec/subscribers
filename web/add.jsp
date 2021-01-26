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
    <div>
        <div>
            <h2>Add user</h2>
        </div>

        <form method="post">
            <label>First name:
                <input type="text" name="firstName" required="required"><br/>
            </label>
            <label>Last name:
                <input type="text" name="lastName" required="required"><br/>
            </label>
            <label>Work phone:
                <input type="text" name="workPhone" required="required"><br/>
            </label>
            <label>Mobile phone:
                <input type="text" name="mobile"><br/>
            </label>
            <label>Email:
                <input type="email" name="email"><br/>
            </label>



            <button type="submit">Отправить</button>
            <button type="reset">Очистить</button>
            <button type="button">Очистить</button>
        </form>
    </div>
</div>

</body>
</html>
