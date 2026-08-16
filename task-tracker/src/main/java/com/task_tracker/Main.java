package com.task_tracker;

import com.task_tracker.service.TaskService;

import java.util.ArrayList;

import com.task_tracker.cli.CommandHandler;
import com.task_tracker.model.Task;
import com.task_tracker.repository.*;
import com.task_tracker.json.JsonParser;

public class Main {
    public static void main(String[] args) {
        TaskRepository repo = new TaskRepository();

        // Step 1: create some tasks and save them
        TaskService taskService = new TaskService();
        taskService.add(new Task("Comprar leite"));
        taskService.add(new Task("Estudar Java"));
        repo.saveToJSON(taskService.getTasks());

        System.out.println("Saved tasks:");
        for (Task t : taskService.getTasks()) {
            System.out.println(t);
        }

        // Step 2: load tasks back from the file (simulating a fresh program start)
        System.out.println("\nLoaded tasks:");
        ArrayList<Task> loadedTasks = repo.loadFromJSON();
        for (Task t : loadedTasks) {
            System.out.println(t);
        }

    }
}