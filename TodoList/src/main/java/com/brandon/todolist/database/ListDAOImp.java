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
    private static final String SQL_INSERT = "INSERT INTO tasks (ownerId, name, description, dueDate, isDone) VALUES (?,?,?,?,0)";
    private static final String SQL_FINISHED = "UPDATE tasks SET isDone = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM tasks WHERE id = ?";
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
                toDoItem.setId(rs.getInt("id"));
                toDoItem.setOwnerId(rs.getInt("ownerId"));
                toDoItem.setName(rs.getString("name"));
                toDoItem.setDescription(rs.getString("description"));
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
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setInt(1, toDoItem.getOwnerId());
            ps.setString(2, toDoItem.getName());
            ps.setString(3, toDoItem.getDescription());
            ps.setDate(4, toDoItem.getDueDate());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public void toggleTaskFinished(int id, boolean isChecked) {
        Connection conn;
        PreparedStatement ps;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_FINISHED);
            if(isChecked){
                ps.setInt(1, 1);
            } else {
                ps.setInt(1, 0);
            }
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void delete(int id){
        Connection conn;
        PreparedStatement ps;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
