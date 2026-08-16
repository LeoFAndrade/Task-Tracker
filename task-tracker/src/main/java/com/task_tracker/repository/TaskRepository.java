package com.task_tracker.repository;

import com.task_tracker.json.JsonParser;
import com.task_tracker.model.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.IOException;

public class TaskRepository {
    Path path = Path.of("tasks.json");
    JsonParser jsonParser = new JsonParser();

    public String toJSON(Task task) {
        return String.format(
                "{\"id\": %d, \"description\": \"%s\", \"status\": \"%s\", \"createdAt\": \"%s\", \"updatedAt\": \"%s\"}",
                task.getId(), task.getDescription(), task.getStatus(), task.getCreatedAt(), task.getUpdatedAt());
    }

    public String tasksToJSON(ArrayList<Task> tasks) {
        ArrayList<String> taskList = new ArrayList<>();
        for (Task task : tasks) {
            taskList.add(toJSON(task));
        }

        String jsonList = String.join(",", taskList);
        return "[" + jsonList + "]";
    }

    public void saveToJSON(ArrayList<Task> tasks) {
        String stringTasks = tasksToJSON(tasks);

        try {
            Files.writeString(path, stringTasks);
        } catch (IOException e) {
            System.out.println("An error occurred while modifying the file: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadFromJSON() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            String fileContent = Files.readString(path);
            String noBrackets = fileContent.substring(1, fileContent.length() - 1);
            ArrayList<String> pieces = jsonParser.jsonToStringArray(noBrackets);
            tasks = jsonParser.jsonToTaskArray(pieces);

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return tasks;
    }
}
