package com.task_tracker;

import com.task_tracker.service.TaskService;

import java.util.ArrayList;

import com.task_tracker.cli.CommandHandler;
import com.task_tracker.model.Task;
import com.task_tracker.repository.*;

public class Main {
    public static void main(String[] args) {
        TaskRepository repository = new TaskRepository();
        CommandHandler handler = new CommandHandler();

        ArrayList<Task> loadedTasks = repository.loadFromJSON();
        TaskService taskService = new TaskService(loadedTasks);
        handler.execute(args, taskService);

        repository.saveToJSON(taskService.getTasks());

    }
}