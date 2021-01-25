<%--
  Created by IntelliJ IDEA.
  User: artemnovikov
  Date: 25.01.2021
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Subscriber</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a class="navbar-brand"> User Subscriber App</a>
        </div>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="insert" method="post">
                <h2>
                    Add New Subscriber
                </h2>

                <fieldset class="form-group">
                    <label>First name</label> <input type="text"
                                                     value="<c:out value='${subscriber.name}' />" class="form-control"
                                                     name="name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Last name</label> <input type="text"
                                                    value="<c:out value='${user.email}' />" class="form-control"
                                                    name="email">
                </fieldset>

                <fieldset class="form-group">
                    <label>Work phone</label> <input type="text"
                                                     value="<c:out value='${user.country}' />"
                                                     class="form-control"
                                                     name="country">
                </fieldset>
                <fieldset class="form-group">
                    <label>Mobile phone</label> <input type="text"
                                                       value="<c:out value='${user.country}' />"
                                                       class="form-control"
                                                       name="country">
                </fieldset>

                <fieldset class="form-group">
                    <label>Email</label> <input type="text"
                                                value="<c:out value='${user.country}' />"
                                                class="form-control"
                                                name="country">
                </fieldset>

                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
