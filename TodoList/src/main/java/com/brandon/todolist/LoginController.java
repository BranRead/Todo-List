package com.brandon.todolist;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.brandon.todolist.dao.LoginDAO;
import com.brandon.todolist.database.ListDAOImp;
import com.brandon.todolist.database.LoginDAOImp;
import com.brandon.todolist.model.ToDoItem;
import com.brandon.todolist.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginController", value = "/login")
public class LoginController extends HttpServlet {
    LoginDAOImp loginDAOImp;
    ListDAOImp listDAOImp;

    public void init() {
        loginDAOImp = new LoginDAOImp();
        listDAOImp = new ListDAOImp();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            update(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            select(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }

    private void select(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        User user = loginDAOImp.select(request.getParameter("username"), request.getParameter("password"));
        if(user != null) {
            request.setAttribute("user", user);
            HttpSession session = request.getSession();
            List<ToDoItem> toDoList = listDAOImp.selectAll(user.getId());
            session.setAttribute("user", user);
            session.setAttribute("list", toDoList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/toDo.jsp");
            dispatcher.include(request, response);
            dispatcher.forward(request, response);

            response.sendRedirect("/toDo.jsp");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        listDAOImp.toggleTaskFinished(Integer.parseInt(request.getParameter("taskId")), Boolean.parseBoolean(request.getParameter("isChecked")));

        Logger logger = Logger.getLogger(getClass().getName());
        logger.fine(request.getParameter("isChecked"));

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<ToDoItem> toDoList = listDAOImp.selectAll(user.getId());

        session.setAttribute("list", toDoList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/toDo.jsp");
        dispatcher.include(request, response);
        dispatcher.forward(request, response);

        response.sendRedirect("/toDo.jsp");

    }
}