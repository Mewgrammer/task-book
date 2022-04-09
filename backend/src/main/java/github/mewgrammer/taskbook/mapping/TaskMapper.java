package github.mewgrammer.taskbook.mapping;

import github.mewgrammer.taskbook.model.dto.CreateTaskDto;
import github.mewgrammer.taskbook.model.dto.TaskDto;
import github.mewgrammer.taskbook.persistence.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TaskMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    Task convertToEntity(CreateTaskDto dto);

    @Mapping(target = "id", source = "externalId")
    TaskDto convertToDto(Task task);
}
