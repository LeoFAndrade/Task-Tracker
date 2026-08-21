package com.task_tracker.repository;

import com.task_tracker.json.JsonParser;
import com.task_tracker.json.JsonWriter;
import com.task_tracker.model.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.IOException;

public class TaskRepository {
    Path path = Path.of("tasks.json");
    JsonParser jsonParser = new JsonParser();
    JsonWriter jsonWriter = new JsonWriter();
    

    public void saveToJSON(ArrayList<Task> tasks) {
        String stringTasks = jsonWriter.tasksToJSON(tasks);

        try {
            Files.writeString(path, stringTasks);
        } catch (IOException e) {
            System.out.println("An error occurred while modifying the file: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadFromJSON() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {

            if (!Files.exists(path)) {
                return tasks;
            }

            String fileContent = Files.readString(path).trim();

            if (fileContent.isEmpty() || fileContent.equals("[]")) {
                return tasks;
            }

            String noBrackets = fileContent.substring(1, fileContent.length() - 1);
            ArrayList<String> pieces = jsonParser.jsonToStringArray(noBrackets);
            tasks = jsonParser.jsonToTaskArray(pieces);

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return tasks;
    }
}
