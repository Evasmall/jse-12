package ru.evasmall.tm.service;

import ru.evasmall.tm.entity.Project;
import ru.evasmall.tm.entity.Task;
import ru.evasmall.tm.repository.ProjectRepository;
import ru.evasmall.tm.repository.TaskRepository;

import java.util.Collections;
import java.util.List;

public class ProjectTaskService {

    private final ProjectRepository projectRepository;

    private final TaskRepository taskRepository;

    public ProjectTaskService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllByProjectId(final Long projectId) {
        if (projectId == null) return Collections.emptyList();
        return taskRepository.findAddByProjectId(projectId);
    }

    public Task removeTaskFromProject(final Long projectId, final Long taskId) {
        final Task task = taskRepository.findByProjectIdAndId(projectId, taskId);
        if (task == null) return null;
        task.setProjectId(null);
        return task;
    }

    public Task addTaskToProject(final Long projectId, final Long taskId) {
        final Project project = projectRepository.findById(projectId);
        if (project == null) return null;
        final Task task = taskRepository.findById(taskId);
        if (task == null) return null;
        task.setProjectId(projectId);
        return task;
    }

    public Project removeProjectByIdWithTask(final Long projectId) {
        final Project project = projectRepository.findById(projectId);
        if (project == null) return null;
        final List<Task> tasks = findAllByProjectId(projectId);
        if (tasks == null) return project;
        for (Task task: tasks) {
            taskRepository.removeById(task.getId());
        }
        projectRepository.removeById(projectId);
        return project;
    }

    public Project removeProjectByIndexWithTask(final int index) {
        final Project project = projectRepository.findByIndex(index);
        if (project == null) return null;
        final List<Task> tasks = findAllByProjectId(project.getId());
        if (tasks == null) return project;
        for (Task task: tasks) {
            taskRepository.removeById(task.getId());
        }
        projectRepository.removeById(project.getId());
        return project;
    }

}
