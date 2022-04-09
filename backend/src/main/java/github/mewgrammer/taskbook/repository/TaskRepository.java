package github.mewgrammer.taskbook.repository;

import github.mewgrammer.taskbook.persistence.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
