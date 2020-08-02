package ru.evasmall.tm;

import ru.evasmall.tm.controller.ProjectController;
import ru.evasmall.tm.controller.SystemController;
import ru.evasmall.tm.controller.TaskController;
import ru.evasmall.tm.repository.ProjectRepository;
import ru.evasmall.tm.repository.TaskRepository;
import ru.evasmall.tm.service.ProjectService;
import ru.evasmall.tm.service.TaskService;

import java.util.Scanner;

import static ru.evasmall.tm.constant.TerminalConst.*;

/**
 * JAVA lesson application. Output terminal parameters.
 */
public class Application {

    private final ProjectRepository projectRepository = new ProjectRepository();
    private final TaskRepository taskRepository = new TaskRepository();

    private final ProjectService projectService = new ProjectService(projectRepository);
    private final TaskService taskService = new TaskService(taskRepository);

    private final ProjectController projectController = new ProjectController(projectService);
    private final TaskController taskController = new TaskController(taskService);
    private final SystemController systemController = new SystemController();

    {
        projectRepository.create("DEMO_PROJECT_1", "DESC PROJECT 1");
        projectRepository.create("DEMO_PROJECT_2", "DESC PROJECT 2");
        taskRepository.create("TEST_TASK_1", "DESC TASK 1");
        taskRepository.create("TEST_TASK_2", "DESC TASK 2");
    }

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final Application application = new Application();
        application.run(args);
        application.systemController.displayWelcome();
        String command = "";
        while (!CMD_EXIT.equals(command)) {
            command = scanner.nextLine();
            application.run(command);
        }
    }

    public void run(final String[] args) {
        if (args == null) return;
        if (args.length < 1) return;
        final String param = args[0];
        final int result = run(param);
        System.exit(result);
    }

    public int run(final String param) {
        if (param == null || param.isEmpty()) return -1;
        switch (param) {
            case CMD_HELP: return systemController.displayHelp();
            case CMD_ABOUT: return systemController.displayAbout();
            case CMD_VERSION: return systemController.displayVersion();
            case CMD_EXIT: return systemController.displayExit();

            case CMD_PROJECT_CREATE: return projectController.createProject();
            case CMD_PROJECT_CLEAR: return projectController.clearProject();
            case CMD_PROJECT_LIST: return projectController.listProject();

            case CMD_PROJECT_VIEW_BY_NAME: return projectController.viewProjectByName();
            case CMD_PROJECT_VIEW_BY_INDEX: return projectController.viewProjectByIndex();
            case CMD_PROJECT_VIEW_BY_ID: return projectController.viewProjectById();

            case CMD_PROJECT_REMOVE_BY_NAME: return projectController.removeProjectByName();
            case CMD_PROJECT_REMOVE_BY_ID: return projectController.removeProjectById();
            case CMD_PROJECT_REMOVE_BY_INDEX: return projectController.removeProjectByIndex();

            case CMD_PROJECT_UPDATE_BY_INDEX: return projectController.updateProjectByIndex();
            case CMD_PROJECT_UPDATE_BY_ID: return projectController.updateProjectById();

            case CMD_TASK_CREATE: return taskController.createTask();
            case CMD_TASK_CLEAR: return taskController.clearTask();
            case CMD_TASK_LIST: return taskController.listTask();

            case CMD_TASK_VIEW_BY_NAME: return taskController.viewTaskByName();
            case CMD_TASK_VIEW_BY_INDEX: return taskController.viewTaskByIndex();
            case CMD_TASK_VIEW_BY_ID: return taskController.viewTaskById();

            case CMD_TASK_REMOVE_BY_NAME: return taskController.removeTaskByName();
            case CMD_TASK_REMOVE_BY_ID: return taskController.removeTaskById();
            case CMD_TASK_REMOVE_BY_INDEX: return taskController.removeTaskByIndex();

            case CMD_TASK_UPDATE_BY_INDEX: return taskController.updateTaskByIndex();
            case CMD_TASK_UPDATE_BY_ID: return taskController.updateTaskById();

            default: return systemController.displayError();
        }
    }

}