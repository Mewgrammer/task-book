package github.mewgrammer.taskbook.service;

import github.mewgrammer.taskbook.persistence.Task;
import github.mewgrammer.taskbook.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository repository;

    public List<Task> getTasks() {
        return repository.findAll();
    }

    public Task getTaskById(long id) {
       return repository.findById(id).orElseThrow();
    }

    public Task addTask(Task task) {
        return repository.save(task);
    }

    public void deleteTask(long id) {
        repository.deleteById(id);
    }
}
