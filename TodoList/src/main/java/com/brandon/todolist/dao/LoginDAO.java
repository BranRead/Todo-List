package com.brandon.todolist.dao;

import com.brandon.todolist.model.User;

import java.sql.SQLException;

public interface LoginDAO {
    public User select(String username, String password) throws SQLException;
}
