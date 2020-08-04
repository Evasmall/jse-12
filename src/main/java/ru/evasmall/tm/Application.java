package ru.evasmall.tm;

import ru.evasmall.tm.controller.ProjectController;
import ru.evasmall.tm.controller.SystemController;
import ru.evasmall.tm.controller.TaskController;
import ru.evasmall.tm.controller.UserController;
import ru.evasmall.tm.entity.enums.RoleEnum;
import ru.evasmall.tm.repository.ProjectRepository;
import ru.evasmall.tm.repository.TaskRepository;
import ru.evasmall.tm.repository.UserRepository;
import ru.evasmall.tm.service.*;

import java.util.Scanner;

import static ru.evasmall.tm.constant.TerminalConst.*;

/**
 * JAVA lesson application. Output terminal parameters.
 */
public class Application {

    private final ProjectRepository projectRepository = new ProjectRepository();
    private final TaskRepository taskRepository = new TaskRepository();
    private final UserRepository userRepository = new UserRepository();

    private final ProjectService projectService = new ProjectService(projectRepository);
    private final TaskService taskService = new TaskService(taskRepository);
    private final ProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);
    private final UserService userService = new UserService(userRepository);

    private final ProjectController projectController = new ProjectController(projectService);
    private final TaskController taskController = new TaskController(taskService, projectTaskService);
    private final SystemController systemController = new SystemController();
    private final UserController userController = new UserController(userService);

    {
        projectRepository.create("DEMO_PROJECT_1", "DESC PROJECT 1");
        projectRepository.create("DEMO_PROJECT_2", "DESC PROJECT 2");
        projectRepository.create("DEMO_PROJECT_3", "DESC PROJECT 3");
        taskRepository.create("TEST_TASK_1", "DESC TASK 1");
        taskRepository.create("TEST_TASK_2", "DESC TASK 2");
        taskRepository.create("TEST_TASK_3", "DESC TASK 3");

        userRepository.create("ADMIN", "Василий", "Чапаев", "Иванович", "chapaev_vi@gmail.com", MD5Hash.getHash("POBEDA", "MD5"), RoleEnum.ADMIN);
        userRepository.create("TEST", "Пётр", "Исаев", "Семёнович", "isaev_ps@gmail.com", MD5Hash.getHash("battalion", "MD5"), RoleEnum.USER);
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
            case CMD_PROJECT_REMOVE_BY_NAME_WITH_TASKS: return taskController.removeProjectByNameWithTasks();
            case CMD_PROJECT_REMOVE_BY_ID_WITH_TASKS: return taskController.removeProjectByIdWithTasks();
            case CMD_PROJECT_REMOVE_BY_INDEX_WITH_TASKS: return taskController.removeProjectByIndexWithTasks();

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

            case CMD_TASK_ADD_TO_PROJECT_BY_IDS: return taskController.addTaskToProjectByIds();
            case CMD_TASK_REMOVE_FROM_PROJECT_BY_IDS: return taskController.removeTaskFromProjectByIds();
            case CMD_TASK_LIST_BY_PROJECT_ID: return taskController.listTaskByProjectId();

            case CMD_USER_REGISTRATION: return userController.createUser();
            case CMD_USER_LIST: return userController.listUser();
            case CMD_USER_REMOVE_BY_LOGIN: return userController.removeUserByLogin();

            default: return systemController.displayError();
        }
    }

}