package com.task_tracker.cli;
import com.task_tracker.model.Task;
import com.task_tracker.service.TaskService;

public class CommandHandler {

    public void execute(String[] args, TaskService tasks) {

    if(args.length==0) {
        System.out.println("Nenhum comando fornecido.");
        return;
    }

    switch(args[0]) {
    case "add":
        String description = args[1];
    Task task = new Task(description); 
    tasks.add(task);
        break;
    case "list":
        // código pra tratar o list
        break;
    default:
        // caso nenhum comando bata (comando inválido)
        break;
        }
    }
}
