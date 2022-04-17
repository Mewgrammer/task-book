package github.mewgrammer.taskbook.api.controller

import github.mewgrammer.taskbook.api.controller.model.UpdateTaskDto
import github.mewgrammer.taskbook.api.model.CreateTaskDto
import github.mewgrammer.taskbook.api.model.Paginated
import github.mewgrammer.taskbook.api.model.PaginationDto
import github.mewgrammer.taskbook.api.model.TaskDto
import github.mewgrammer.taskbook.mapping.TaskMapper
import github.mewgrammer.taskbook.security.annotations.*
import github.mewgrammer.taskbook.security.model.Privilege
import github.mewgrammer.taskbook.security.model.Role
import github.mewgrammer.taskbook.service.TaskService
import lombok.AllArgsConstructor
import org.springdoc.api.annotations.ParameterObject
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.UUID
import javax.validation.Valid

@RestController
@AllArgsConstructor
@RequestMapping("api/tasks")
class TaskController(private val taskMapper: TaskMapper, private val taskService: TaskService) : TaskApi {

    @GetMapping
    @HasPrivilege(Privilege.READ)
    override fun getTasks(@Valid @ParameterObject pagination: PaginationDto, auth: Authentication): Paginated<TaskDto> {
        val page = taskService.getTasks(pagination, auth).map { taskMapper.convertToDto(it)  }
        return Paginated.of(page);
    }


    @PostMapping
    @HasAccess(Role.USER, Privilege.WRITE)
    override fun addTask(@Valid @RequestBody createTaskData: CreateTaskDto): TaskDto {
        val task = taskMapper.convertToEntity(createTaskData)
        return taskMapper.convertToDto(taskService.addTask(task))
    }

    @PutMapping("{id}")
    @HasAccess(Role.USER, Privilege.WRITE)
    override fun updateTask(
        @PathVariable id: UUID,
        @Valid @RequestBody updateTaskData: UpdateTaskDto,
        auth: Authentication
    ): TaskDto {
        return taskMapper.convertToDto(taskService.updateTask(auth, id, updateTaskData))
    }

    @DeleteMapping("{id}")
    @HasAccess(Role.USER, Privilege.WRITE)
    override fun deleteTask(@PathVariable id: UUID) {
        taskService.deleteTask(id)
    }
}
