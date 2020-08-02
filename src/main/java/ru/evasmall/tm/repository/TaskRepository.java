package ru.evasmall.tm.repository;

import ru.evasmall.tm.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    public List<Task> findAll() {
        return tasks;
    }

    public List<Task> findAllByProjectId(final Long projectId) {
        final List<Task> result = new ArrayList<>();
        for (final Task task: findAll()) {
            if (task.getProjectId() == null) continue;
            if (task.getProjectId().equals(projectId)) result.add(task);
        }
        return result;
    }


    public Task create(final String name) {
        final Task task = new Task(name);
        tasks.add(task);
        return task;
    }

    public Task create(final String name, String description) {
        final Task task = new Task(name);
        task.setName(name);
        task.setDescription(description);
        tasks.add(task);
        return task;
    }

    public Task update(final Long id, final String name, String description) {
        final Task task = findById(id);
        if (task == null) return null;
        task.setId(id);
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    public List<Task> findAddByProjectId(final Long projectId) {
        final List<Task> result = new ArrayList<>();
        for (final Task task: findAll()) {
            final Long idProject = task.getProjectId();
            if (idProject == null) continue;
            if (idProject.equals(projectId)) result.add(task);
        }
        return result;
    }

    public void clear() {
        tasks.clear();
    }

    public Task findByName(final String name) {
        for (final Task task: tasks) {
            if(task.getName().equals(name)) return task;
        }
        return null;
    }

    public Task findById(final Long id) {
        for (final Task task: tasks) {
            if(task.getId().equals(id)) return task;
        }
        return null;
    }

    public Task findByProjectIdAndId(final Long projectId, final Long id) {
        for (final Task task: tasks) {
            final Long idProject = task.getProjectId();
            if (idProject == null) continue;
            if (!idProject.equals(projectId)) continue;
            if(task.getId().equals(id)) return task;
        }
        return null;
    }

    public Task findByIndex(final int index) {
        return tasks.get(index);
    }

    public Task removeById (final Long id) {
        final Task task = findById(id);
        if (task == null) return null;
        tasks.remove(task);
        return task;
    }

    public Task removeByIndex (final int index) {
        final Task task = findByIndex(index);
        if (task == null) return null;
        tasks.remove(task);
        return task;
    }

    public Task removeByName (final String name) {
        final Task task = findByName(name);
        if (task == null) return null;
        tasks.remove(task);
        return task;
    }

    public int size() {
        return tasks.size();
    }

}