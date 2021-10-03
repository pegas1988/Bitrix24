<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 13.06.2021
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DeleteMaterialPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
        integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
        crossorigin="anonymous"></script>

<form method="post">
    <center>
        <div class="container m-3">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Название</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <c:forEach items="${list}" var="task">
                <tr>
                    <td><c:out value="${task.name}"/></td>
                    <td>
                        <form method="post">
                            <div class="d-grid gap-2">
                                <input type="hidden" name="id" value="${task.id}">
                                <button name="Accept" type="Submit" class="btn btn-secondary">
                                    Детальнее
                                </button>
                            </div>
                        </form>
                    </td>
                </tr>
                </c:forEach>
                </tr>
                </tbody>
            </table>
        </div>
    </center>
</form>
</body>
</html>
