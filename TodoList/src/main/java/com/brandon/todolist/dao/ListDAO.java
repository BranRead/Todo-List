package com.brandon.todolist.dao;

import com.brandon.todolist.model.ToDoItem;

import java.util.List;

public interface ListDAO {
    public List<ToDoItem> selectAll(int id);
    public void addNewTask(ToDoItem toDoItem);

    public void delete(int id);
    public void toggleTaskFinished(int id, boolean isChecked);
}
