package com.task_tracker;

import java.util.Scanner;
import com.task_tracker.service.TaskService;
import com.task_tracker.model.Task;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskService tasks = new TaskService();

        System.out.print("Crie uma nova tarefa: ");
        String description = scanner.nextLine();
        Task task = new Task(description);
        tasks.add(task);

        System.out.println("\nTarefas cadastradas:");
        for (Task t : tasks.getTasks()) {
            System.out.println(t);
        }

        scanner.close();
    }
}
