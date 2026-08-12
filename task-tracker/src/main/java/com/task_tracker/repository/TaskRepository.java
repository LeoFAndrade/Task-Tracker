package com.task_tracker.repository;

import com.task_tracker.model.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.IOException;

public class TaskRepository {
    Path path = Path.of("tasks.json");

    public String toJSON(Task task) {
        return String.format(
                "{\"id\": %d, \"description\": \"%s\", \"status\": \"%s\", \"createdAt\": \"%s\", \"updatedAt\": \"%s\"}",
                task.getId(), task.getDescription(), task.getStatus(), task.getCreatedAt(), task.getUpdatedAt());
    }

    public String tasksToJson(ArrayList<Task> tasks) {
        ArrayList<String> taskList = new ArrayList<>();
        for (Task task : tasks) {
            taskList.add(toJSON(task));
        }

        String jsonList = String.join(",", taskList);
        return "[" + jsonList + "]";
    }

    public void saveToJSON(ArrayList<Task> tasks) {
        String stringTasks = tasksToJson(tasks);

        try {
            Files.writeString(path, stringTasks);
        } catch (IOException e) {
            System.out.println("An error occurred while modifying the filea: " + e.getMessage());
        }
    }
}
