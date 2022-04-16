package github.mewgrammer.taskbook.mapping;

import github.mewgrammer.taskbook.api.controller.model.UpdateTaskDto;
import github.mewgrammer.taskbook.api.model.CreateTaskDto;
import github.mewgrammer.taskbook.api.model.TaskDto;
import github.mewgrammer.taskbook.persistence.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TaskMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    Task convertToEntity(CreateTaskDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    Task convertToEntity(UpdateTaskDto dto);

    @Mapping(target = "id", source = "externalId")
    @Mapping(target = "creator", source = "createdBy")
    TaskDto convertToDto(Task task);
}
