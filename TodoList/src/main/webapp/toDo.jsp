<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: Brandon
  Date: 2/19/2024
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>To Do List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="stylesheets/styles.css">
    <script src="scripts/script.js"></script>
</head>

<body style="background-color: #1a1a1a">
<script>
    function editTask(){
        console.log("Test")
    }
</script>

    <div class="container">
        <div class="row mt-5">
            <div class="offset-3 col-6 d-flex flex-column justify-content-end bg-info rounded border-white">
                <table class="table mt-3">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Task</th>
                        <th scope="col">Description</th>
                        <th scope="col">When to Complete</th>
                        <th colspan="2">Options</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${sessionScope.list}">
                        <tr>
                            <c:choose>
                                <c:when test="${item.isDone() == true}">
                                    <th><input type="checkbox" value="${item.getId()}" onClick="toggleDone(${item.getId()}, this)" checked/></th>
                                    <td class="crossedOut"><c:out value="${item.getName()}"/></td>
                                    <td class="crossedOut"><c:out value="${item.getDescription()}"/></td>
                                    <td class="crossedOut"><c:out value="${item.getDueDate()}"/></td>
                                </c:when>
                                <c:otherwise>
                                    <th><input type="checkbox" value="${item.getId()}" onClick="toggleDone(${item.getId()}, this)"/></th>
                                    <td><c:out value="${item.getName()}"/></td>
                                    <td><c:out value="${item.getDescription()}"/></td>
                                    <td><c:out value="${item.getDueDate()}"/></td>
                                </c:otherwise>
                            </c:choose>

                            <td><button class="edit btn btn-primary" name="edit" value="${item.getId()}" onClick="editTask()">Edit</button></td>
                            <td><form action="list" method="post"><button class="btn btn-danger" type="submit" name="delete" value="${item.getId()}">Delete</button></form></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <button class="ms-auto mb-3 btn btn-primary" data-bs-toggle="modal" data-bs-target="#addTaskModal">Add task</button>

                <div class="modal fade" id="addTaskModal" tabindex="-1" aria-labelledby="addTaskModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content bg-info border-white">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5 mx-auto" id="addTaskModalLabel">Add New Task</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body d-flex flex-column justify-content-end">
                                <form action="list" method="post">
                                    <label class="form-label" for="taskName"></label>
                                    <input id="taskName" class="form-control" type="text" name="name" placeholder="Name"/>
                                    <label class="form-label" for=description></label>
                                    <textarea id="description" class="form-control" name="description" rows="5"></textarea>
                                    <label class="form-label" for="dueDate"></label>
                                    <input id="dueDate" class="form-control"  type="date" name="date"/>
                                    <button class="btn btn-primary mt-2 ms-auto" name="add" value="${sessionScope.user.getId()}">Add</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>
