package com.task_tracker.json;

import java.time.LocalDateTime;
import java.util.ArrayList;
import com.task_tracker.model.*;

public class JsonParser {

    public ArrayList<String> jsonToStringArray(String jsonArray) {
        ArrayList<String> stringList = new ArrayList<>();

        if (jsonArray == null || jsonArray.trim().isEmpty()) {
            return stringList;
        }

        String objectString = "";
        int depth = 0;
        int start = 0;

        for (int i = 0; i < jsonArray.length(); i++) {
            char actualChar = jsonArray.charAt(i);

            if (actualChar == '{') {
                depth++;
            }

            if (actualChar == '}') {
                depth--;
            }

            if (actualChar == ',' && depth == 0) {
                objectString = jsonArray.substring(start, i);
                start = i + 1;
                stringList.add(objectString);
            }
        }
        stringList.add(jsonArray.substring(start, jsonArray.length()));
        return stringList;
    }

    public Task jsonToTask(String jsonObject) {
        String withoutBraces = jsonObject.replaceAll("[{}]", "");

        ArrayList<String> fields = new ArrayList<>();
        int start = 0;
        boolean insideQuotes = false;

        for (int i = 0; i < withoutBraces.length(); i++) {
            char c = withoutBraces.charAt(i);

            if (c == '"') {
                insideQuotes = !insideQuotes;
            }

            if (c == ',' && !insideQuotes) {
                fields.add(withoutBraces.substring(start, i));
                start = i + 1;
            }
        }
        fields.add(withoutBraces.substring(start, withoutBraces.length()));

        int id = Integer.parseInt(fields.get(0).replaceAll("\"", "").split(":", 2)[1].strip());
        String description = fields.get(1).replaceAll("\"", "").split(":", 2)[1].strip();
        Status status = Status.valueOf(fields.get(2).replaceAll("\"", "").split(":", 2)[1].strip());
        LocalDateTime createdAt = LocalDateTime.parse(fields.get(3).replaceAll("\"", "").split(":", 2)[1].strip());
        LocalDateTime updatedAt = LocalDateTime.parse(fields.get(4).replaceAll("\"", "").split(":", 2)[1].strip());

        Task task = new Task(description, id, status, createdAt, updatedAt);
        return task;
    }

    public ArrayList<Task> jsonToTaskArray(ArrayList<String> jsonArray) {
        ArrayList<Task> taskList = new ArrayList<>();

        for (String string : jsonArray) {
            taskList.add(jsonToTask(string));
        }

        return taskList;
    }
}
