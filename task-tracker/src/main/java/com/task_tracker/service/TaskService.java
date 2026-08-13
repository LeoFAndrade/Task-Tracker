package com.task_tracker.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import com.task_tracker.model.Task;
import com.task_tracker.model.Status;

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

    public void update(int id, String description) {
        Task foundTask = findTaskById(id);

        if (foundTask != null) {
            foundTask.setDescription(description);
            foundTask.setUpdatedAt(LocalDateTime.now());
        } else {
            System.out.println(String.format("task-cli %d doesn't exist", id));
        }
    }

    public void delete(int id) {
        Task foundTask = findTaskById(id);

        if (foundTask != null) {
            tasks.remove(foundTask);
        } else {
            System.out.println(String.format("task-cli %d doesn't exist", id));
        }
    }

    public void markStatus(int id, Status status) {
        Task foundTask = findTaskById(id);

        if (foundTask != null) {
            foundTask.setStatus(status);
            foundTask.setUpdatedAt(LocalDateTime.now());
        } else {
            System.out.println(String.format("task-cli %d doesn't exist", id));
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> getDoneTasks() {
        return tasks;
    }

    public ArrayList<Task> getNotDoneTasks() {
        return tasks;
    }

    public ArrayList<Task> getInProgressTasks() {
        return tasks;
    }

    public Task findTaskById(int id) {
        Task foundTask = null;

        for (Task task : tasks) {
            if (task.getId() == id) {
                foundTask = task;
            }
        }

        return foundTask;
    }

}
