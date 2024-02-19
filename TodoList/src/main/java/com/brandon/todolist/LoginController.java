package com.brandon.todolist;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.brandon.todolist.dao.LoginDAO;
import com.brandon.todolist.database.LoginDAOImp;
import com.brandon.todolist.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginController", value = "/login")
public class LoginController extends HttpServlet {
    LoginDAOImp loginDAOImp;

    public void init() {
        loginDAOImp = new LoginDAOImp();
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
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.include(request, response);
        dispatcher.forward(request, response);

        response.sendRedirect("/index.jsp");
    }
}