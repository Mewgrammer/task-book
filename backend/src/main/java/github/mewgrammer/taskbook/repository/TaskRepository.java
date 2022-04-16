package github.mewgrammer.taskbook.repository;

import github.mewgrammer.taskbook.persistence.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCreatedBy(String createdBy);
    Optional<Task> findByExternalId(UUID id);
    Optional<Task> findByExternalIdAndCreatedBy(UUID externalId, String createdBy);
}
