package github.mewgrammer.taskbook.service;

import github.mewgrammer.taskbook.api.controller.model.UpdateTaskDto;
import github.mewgrammer.taskbook.api.model.PaginationDto;
import github.mewgrammer.taskbook.persistence.Task;
import github.mewgrammer.taskbook.persistence.repository.TaskRepository;
import github.mewgrammer.taskbook.security.helpers.PermissionHelper;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository repository;

    public List<Task> getTasks() {
        return repository.findAll();
    }

    public Page<Task> getTasks(PaginationDto pagination) {
        return repository.findAll(PageRequest.of(pagination.getPage(), pagination.getSize()));
    }

    public Page<Task> getTasks(PaginationDto pagination, @NotNull Authentication auth) {
        var pageable = PageRequest.of(pagination.getPage(), pagination.getSize());
        var user = (User) auth.getPrincipal();
        return PermissionHelper.userIsAdmin(user)? getTasks(pagination) : repository.findAllByCreatedBy(user.getUsername(), pageable);
    }

    public List<Task> getTasks(@NotNull Authentication auth) {
        var user = (User) auth.getPrincipal();
        return PermissionHelper.userIsAdmin(user)? repository.findAll() : repository.findAllByCreatedBy(user.getUsername());
    }

    public Task getTaskById(long id) {
        return repository.findById(id).orElseThrow();
    }

    public Task addTask(Task task) {
        return repository.save(task);
    }

    public void deleteTask(UUID id) {
        repository.deleteByExternalId(id);
    }

    public Task updateTask(@NotNull Authentication auth, @NotNull UUID id, @NotNull UpdateTaskDto updateTaskData) {
        var user = (User) auth.getPrincipal();
        if (PermissionHelper.userIsAdmin(user)) return updateTask(id, updateTaskData);
        var existingTask = repository.findByExternalIdAndCreatedBy(id, user.getUsername()).orElseThrow();
        return updateTask(existingTask, updateTaskData);
    }

    public Task updateTask(@NotNull UUID id, @NotNull UpdateTaskDto updateTaskData) {
        var existingTask = repository.findByExternalId(id).orElseThrow();
        return updateTask(existingTask, updateTaskData);
    }

    public Task updateTask(@NotNull Task task, @NotNull UpdateTaskDto updateTaskData) {
        task.setDescription(updateTaskData.getDescription());
        task.setTitle(updateTaskData.getTitle());
        task.setStatus(updateTaskData.getState());
        return repository.save(task);
    }
}
