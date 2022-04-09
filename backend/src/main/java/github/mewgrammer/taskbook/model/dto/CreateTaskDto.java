package github.mewgrammer.taskbook.model.dto;

import github.mewgrammer.taskbook.model.TaskState;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDto {
    @NotBlank private String title;
    private String description;
    private TaskState status = TaskState.PENDING;
}