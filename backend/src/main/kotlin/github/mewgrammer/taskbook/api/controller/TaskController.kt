package github.mewgrammer.taskbook.api.controller

import github.mewgrammer.taskbook.api.controller.model.UpdateTaskDto
import github.mewgrammer.taskbook.api.model.CreateTaskDto
import github.mewgrammer.taskbook.api.model.TaskDto
import github.mewgrammer.taskbook.mapping.TaskMapper
import github.mewgrammer.taskbook.security.annotations.ReadPrivilege
import github.mewgrammer.taskbook.security.annotations.UserAuthorization
import github.mewgrammer.taskbook.security.annotations.WritePrivilege
import github.mewgrammer.taskbook.service.TaskService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import lombok.AllArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.UUID
import javax.validation.Valid

@RestController
@AllArgsConstructor
@RequestMapping("api/tasks")
class TaskController(private val taskMapper: TaskMapper, private val taskService: TaskService) : TaskApi {

    @GetMapping
    @ReadPrivilege
    override fun getTasks(auth: Authentication): List<TaskDto> {
        return taskService.getTasks(auth).map { taskMapper.convertToDto(it) }.toList()
    }


    @PostMapping
    @UserAuthorization
    @WritePrivilege
    override fun addTask(@Valid @RequestBody createTaskData: CreateTaskDto): TaskDto {
        val task = taskMapper.convertToEntity(createTaskData)
        return taskMapper.convertToDto(taskService.addTask(task))
    }

    @PutMapping("{id}")
    @UserAuthorization
    @WritePrivilege
    override fun updateTask(
        @PathVariable id: UUID,
        @Valid @RequestBody updateTaskData: UpdateTaskDto,
        auth: Authentication
    ): TaskDto {
        return taskMapper.convertToDto(taskService.updateTask(auth, id, updateTaskData))
    }

    @DeleteMapping("{id}")
    @UserAuthorization
    @WritePrivilege
    override fun deleteTask(@PathVariable id: Long) {
        taskService.deleteTask(id)
    }
}
