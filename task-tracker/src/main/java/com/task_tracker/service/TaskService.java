package com.task_tracker.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.task_tracker.model.Task;
import com.task_tracker.model.Status;

public class TaskService {
    ArrayList<Task> tasks;

    public TaskService(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public void add(Task task) {
        int maxId = -1;

        for (Task t : tasks) {
            if (t.getId() > maxId) {
                
                maxId = t.getId();
            }
        }

        task.setId(maxId + 1);
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
        ArrayList<Task> doneTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getStatus() == Status.DONE) {
                doneTasks.add(task);
            }
        }
        return doneTasks;
    }

    public ArrayList<Task> getNotDoneTasks() {
        ArrayList<Task> notDoneTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getStatus() == Status.TODO) {
                notDoneTasks.add(task);
            }
        }
        return notDoneTasks;
    }

    public ArrayList<Task> getInProgressTasks() {
        ArrayList<Task> inProgressTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getStatus() == Status.IN_PROGRESS) {
                inProgressTasks.add(task);
            }
        }
        return inProgressTasks;
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
