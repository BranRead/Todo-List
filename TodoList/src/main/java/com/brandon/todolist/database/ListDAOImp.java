package com.brandon.todolist.database;

import com.brandon.todolist.dao.ListDAO;
import com.brandon.todolist.model.ToDoItem;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.brandon.todolist.database.MySQLConnection.getConnection;

public class ListDAOImp implements ListDAO {
    private static final String SQL_SELECT_ALL = "SELECT * FROM tasks where ownerId = ?";
    private static final String SQL_INSERT = "INSERT INTO tasks (ownerId, name, dueDate, isDone) VALUES (?,?,?,0)";
    private Connection jdbcConnection;


    @Override
    public List<ToDoItem> selectAll(int id) {
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;

        List<ToDoItem> toDoList = new ArrayList<>();

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_SELECT_ALL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                ToDoItem toDoItem = new ToDoItem();
                toDoItem.setOwnerId(rs.getInt("ownerId"));
                toDoItem.setName(rs.getString("name"));
                toDoItem.setDueDate(rs.getDate("dueDate"));
                toDoItem.setDone(rs.getBoolean("isDone"));
                toDoList.add(toDoItem);
            }
        } catch (Exception e) {
            ToDoItem toDoItem = new ToDoItem();
            toDoItem.setName("Error");
            toDoList.add(toDoItem);
            System.out.println("Error: " + e.getMessage());
        }
        return toDoList;
    }

    @Override
    public void addNewTask(ToDoItem toDoItem) {
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setInt(1, toDoItem.getOwnerId());
            ps.setString(2, toDoItem.getName());
            ps.setDate(3, toDoItem.getDueDate());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
