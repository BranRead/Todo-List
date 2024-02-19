package com.brandon.todolist;

import com.brandon.todolist.database.ListDAOImp;
import com.brandon.todolist.model.ToDoItem;
import com.brandon.todolist.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "listController", value= "/list")
public class ListController extends HttpServlet {
    ListDAOImp listDAOImp;

    public void init() {
        listDAOImp = new ListDAOImp();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        try {
            add(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy(){

    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setName(request.getParameter("name"));
        toDoItem.setDueDate(Date.valueOf(request.getParameter("date")));
        toDoItem.setOwnerId(Integer.parseInt(request.getParameter("userId")));
        listDAOImp.addNewTask(toDoItem);


        List<ToDoItem> toDoList = listDAOImp.selectAll(Integer.parseInt(request.getParameter("userId")));


        request.getSession().setAttribute("list", toDoList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/toDo.jsp");
        dispatcher.include(request, response);
        dispatcher.forward(request, response);
        response.sendRedirect("/toDo.jsp");
    }
}
