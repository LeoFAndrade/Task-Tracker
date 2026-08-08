package com.task_tracker.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import com.task_tracker.model.Task;

public class TaskService {
    ArrayList<Task> tasks = new ArrayList<>();
    int nextId = 0;

    public void add(Task task) {
        for (Task t : tasks) {
            if (t.getId() > nextId) {
                nextId = t.getId();
            }
        }

        task.setId(nextId++);
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
