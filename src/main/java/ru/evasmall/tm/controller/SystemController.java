package ru.evasmall.tm.controller;

public class SystemController {

    public void displayWelcome() {
        System.out.println("*** WELCOME TO TASK MANAGER! ***");
    }

    public int displayExit() {
        System.out.println("Terminate program. Goodbye!");
        return 0;
    }

    public int displayError() {
        System.out.println("ERROR! Unknown program argument.");
        return -1;
    }

    public int displayHelp() {
        System.out.println("version - Display program version.");
        System.out.println("about - Display developer info.");
        System.out.println("help - Display list of terminal commands.");
        System.out.println("exit - Terminate console application.");
        System.out.println();
        System.out.println("project-list - Display list of projects.");
        System.out.println("project-create - Create new project by name.");
        System.out.println("project-clear - Remove all projects.");
        System.out.println("project-view-by-name - View project by name");
        System.out.println("project-view-by-index - View project by index.");
        System.out.println("project-view-by-id - View project by id.");
        System.out.println();
        System.out.println("project-remove-by-name - Remove project by name.");
        System.out.println("project-remove-by-id - Remove project by id.");
        System.out.println("project-remove-by-id-with-tasks - Remove project by id with tasks.");
        System.out.println("project-remove-by-index - Remove project by index.");
        System.out.println("project-update-by-id - Update project by id.");
        System.out.println("project-update-by-index - Update project by index.");
        System.out.println();
        System.out.println("task-list - Display list of tasks.");
        System.out.println("task-create - Create new task by name.");
        System.out.println("task-clear - Remove all tasks.");
        System.out.println("task-view-by-name - View task by name");
        System.out.println("task-view-by-index - View task by index.");
        System.out.println("task-view-by-id - View task by id.");
        System.out.println();
        System.out.println("task-remove-by-name - Remove task by name.");
        System.out.println("task-remove-by-id - Remove task by id.");
        System.out.println("task-remove-by-index - Remove task by index.");
        System.out.println("task-update-by-id - Update task by id.");
        System.out.println("task-update-by-index - Update task by index.");
        System.out.println();
        System.out.println("task-list-by-project-id - Display task list by project id.");
        System.out.println("task-add-to_project-by-ids - Add task to project by ids.");
        System.out.println("task-remove-from-project-by-ids - Remove task from project by ids.");
        return 0;
    }

    public int  displayVersion() {
        System.out.println("1.0.0");
        return 0;
    }

    public int  displayAbout() {
        System.out.println("Evgeniya Smolkina");
        System.out.println("smolkina_ev@nlmk.com");
        return 0;
    }

}
