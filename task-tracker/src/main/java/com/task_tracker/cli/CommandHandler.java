package com.task_tracker.cli;

import com.task_tracker.model.Task;
import com.task_tracker.model.Status;
import com.task_tracker.service.TaskService;

public class CommandHandler {

    public void execute(String[] args, TaskService tasks) {

        if (args.length == 0) {
            System.out.println("Nenhum comando fornecido.");
            return;
        }

        switch (args[0]) {
            case "add":
                String description = args[1];
                Task task = new Task(description);
                tasks.add(task);
                break;

            case "update":
                int id = Integer.parseInt(args[1]);
                description = args[2];
                tasks.update(id, description);
                break;

            case "delete":
                id = Integer.parseInt(args[1]);
                tasks.delete(id);
                break;

            case "list":

                if (args.length > 1) {

                    switch (args[1]) {
                        case "done":
                            for (Task t : tasks.getDoneTasks()) {
                                System.out.println(t);
                            }
                            break;

                        case "todo":
                            for (Task t : tasks.getNotDoneTasks()) {
                                System.out.println(t);
                            }
                            break;

                        case "in-progress":
                            for (Task t : tasks.getInProgressTasks()) {
                                System.out.println(t);
                            }
                            break;

                        default:
                            break;
                    }

                } else {
                    for (Task t : tasks.getTasks()) {
                        System.out.println(t);
                    }
                }

                break;

            case "mark-in-progress":
                id = Integer.parseInt(args[1]);
                Status status = Status.IN_PROGRESS;
                tasks.markStatus(id, status);
                break;

            case "mark-done":
                id = Integer.parseInt(args[1]);
                status = Status.DONE;
                tasks.markStatus(id, status);
                break;

            default:
                break;
        }
    }
}
