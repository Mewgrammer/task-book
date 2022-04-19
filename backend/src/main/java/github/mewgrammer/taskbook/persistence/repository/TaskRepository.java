package github.mewgrammer.taskbook.persistence.repository;

import github.mewgrammer.taskbook.persistence.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByCreatedBy(String createdBy, Pageable pageable);
    List<Task> findAllByCreatedBy(String createdBy);
    Optional<Task> findByExternalId(UUID id);
    Optional<Task> findByExternalIdAndCreatedBy(UUID externalId, String createdBy);
    void deleteByExternalId(UUID id);
}
