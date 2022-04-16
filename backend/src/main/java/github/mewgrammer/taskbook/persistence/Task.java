package github.mewgrammer.taskbook.persistence;

import github.mewgrammer.taskbook.model.TaskState;
import github.mewgrammer.taskbook.persistence.audit.AuditableEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task extends AuditableEntity {

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private TaskState status;
}
