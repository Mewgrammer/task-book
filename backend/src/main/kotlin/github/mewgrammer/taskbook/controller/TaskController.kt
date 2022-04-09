package github.mewgrammer.taskbook.controller

import github.mewgrammer.taskbook.mapping.TaskMapper
import github.mewgrammer.taskbook.model.dto.CreateTaskDto
import github.mewgrammer.taskbook.model.dto.TaskDto
import github.mewgrammer.taskbook.persistence.Task
import github.mewgrammer.taskbook.security.annotations.ReadPrivilege
import github.mewgrammer.taskbook.security.annotations.UserAuthorization
import github.mewgrammer.taskbook.security.annotations.WritePrivilege
import github.mewgrammer.taskbook.security.model.Privilege
import github.mewgrammer.taskbook.security.model.Role
import github.mewgrammer.taskbook.service.TaskService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/tasks")
@AllArgsConstructor
class TaskController(private val taskMapper: TaskMapper, private val taskService: TaskService) {

    @GetMapping
    @ReadPrivilege
    fun getTasks(): List<TaskDto> {
        return taskService.tasks.map { taskMapper.convertToDto(it) }.toList()
    }

    @PostMapping
    @UserAuthorization
    @WritePrivilege
    fun addTask(@Valid @RequestBody createTaskData: CreateTaskDto): TaskDto {
        val task = taskMapper.convertToEntity(createTaskData)
        return taskMapper.convertToDto(taskService.addTask(task))
    }

    @DeleteMapping("{id}")
    @UserAuthorization
    @WritePrivilege
    fun deleteTask(@PathVariable id: Long) {
        taskService.deleteTask(id)
    }
}
