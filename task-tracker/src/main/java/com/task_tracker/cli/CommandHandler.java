package com.task_tracker.cli;

import com.task_tracker.model.Task;
import com.task_tracker.model.Status;
import com.task_tracker.service.TaskService;

public class CommandHandler {

    public void execute(String[] args, TaskService tasks) {

        if (args.length == 0) {
            System.out.println("No command provided.");
            return;
        }

        switch (args[0]) {
            case "add":
                if (args.length < 2) {
                    System.out.println("No description provided");
                    break;
                }
                String description = args[1];
                Task task = new Task(description);
                tasks.add(task);
                break;

            case "update":
                if (args.length < 3) {
                    System.out.println("No description provided");
                    break;
                }
                try {
                    int id = Integer.parseInt(args[1]);
                    description = args[2];
                    tasks.update(id, description);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid id: " + args[1] + " is not a number");
                }

                break;

            case "delete":
                if (args.length < 2) {
                    System.out.println("No id provided");
                    break;
                }
                try {
                    int id = Integer.parseInt(args[1]);
                    tasks.delete(id);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid id: " + args[1] + " is not a number");
                }

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
                if (args.length < 2) {
                    System.out.println("No id provided");
                    break;
                }
                try {
                    int id = Integer.parseInt(args[1]);
                    Status status = Status.IN_PROGRESS;
                    tasks.markStatus(id, status);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid id: " + args[1] + " is not a number");
                }

                break;

            case "mark-done":
                if (args.length < 2) {
                    System.out.println("No id provided");
                    break;
                }
                try {
                    int id = Integer.parseInt(args[1]);
                    Status status = Status.DONE;
                    tasks.markStatus(id, status);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid id: " + args[1] + " is not a number");
                }

                break;

            default:
                System.out.println("Unknown Command");
                break;
        }
    }
}
