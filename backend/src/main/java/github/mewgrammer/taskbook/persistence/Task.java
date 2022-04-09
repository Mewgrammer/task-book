package github.mewgrammer.taskbook.persistence;

import github.mewgrammer.taskbook.model.TaskState;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task extends BaseEntity {

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private TaskState status;
}
