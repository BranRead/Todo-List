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

@WebServlet("/list")
public class ListController extends HttpServlet {
    ListDAOImp listDAOImp;

    public void init() {
        listDAOImp = new ListDAOImp();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            addTest();
//            select(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try{
            if(request.getParameter("add") != null) {
                add(request, response);
            }
            if(request.getParameter("delete") != null) {
                delete(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy(){

    }

    private void add(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setName(request.getParameter("name"));
        toDoItem.setDueDate(Date.valueOf(request.getParameter("date")));
        toDoItem.setOwnerId(Integer.parseInt(request.getParameter("add")));
        listDAOImp.addNewTask(toDoItem);
        List<ToDoItem> toDoList = listDAOImp.selectAll(Integer.parseInt(request.getParameter("add")));
        request.getSession().setAttribute("list", toDoList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/toDo.jsp");
        dispatcher.include(request, response);
        dispatcher.forward(request, response);
        response.sendRedirect("/toDo.jsp");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        listDAOImp.delete(Integer.parseInt(request.getParameter("delete")));
        User user = (User) request.getSession().getAttribute("user");
        List<ToDoItem> toDoList = listDAOImp.selectAll(user.getId());
        request.getSession().setAttribute("list", toDoList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/toDo.jsp");
        dispatcher.include(request, response);
        dispatcher.forward(request, response);
        response.sendRedirect("/toDo.jsp");
    }

//    private void select(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
//
//
////            request.setAttribute("user", user);
//            HttpSession session = request.getSession();
//            User user = (User) session.getAttribute("user");
//            List<ToDoItem> toDoList = listDAOImp.selectAll(user.getId());
////            session.setAttribute("user", user);
//            session.setAttribute("list", toDoList);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/toDo.jsp");
//            dispatcher.include(request, response);
//            dispatcher.forward(request, response);
//
//            response.sendRedirect("/toDo.jsp");
//
//    }

    private void addTest() {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setName("This worked!!!!");
        listDAOImp.addNewTask(toDoItem);
    }


}
