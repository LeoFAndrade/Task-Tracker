package com.task_tracker.json;

import java.util.ArrayList;

import com.task_tracker.model.Task;

public class JsonWriter {
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
}
