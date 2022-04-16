package github.mewgrammer.taskbook.api.controller

import github.mewgrammer.taskbook.api.controller.model.UpdateTaskDto
import github.mewgrammer.taskbook.api.model.CreateTaskDto
import github.mewgrammer.taskbook.api.model.TaskDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@Tag(name = "task", description = "API to perform CRUD Operations on Tasks")
interface TaskApi {

    @Operation(summary = "Gets all Tasks")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Returns all Tasks for the requesting user",
                content = [
                    Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = TaskDto::class))
                    )
                ]
            ),
            ApiResponse(
                responseCode = "400", description = "Invalid data supplied",
            ),
            ApiResponse(
                responseCode = "406", description = "Task already exists",
            )
        ]
    )
    @GetMapping
    fun getTasks(auth: Authentication): List<TaskDto>

    @Operation(summary = "Update a Task")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "The Task was created",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = TaskDto::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "400", description = "Invalid data supplied",
            ),
            ApiResponse(
                responseCode = "406", description = "Task already exists",
            )
        ]
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun addTask(@Valid @RequestBody createTaskData: CreateTaskDto): TaskDto

    @Operation(summary = "Updates a Task")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "The Task was updated",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = TaskDto::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "400", description = "Invalid update-data supplied",
            ),
            ApiResponse(
                responseCode = "404", description = "Task not found",
            )
        ]
    )
    fun updateTask(
        @PathVariable id: UUID,
        @Valid @RequestBody updateTaskData: UpdateTaskDto,
        auth: Authentication
    ): TaskDto

    @Operation(summary = "Deletes a Task with a given ID")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "The Task was deleted",
            ),
            ApiResponse(
                responseCode = "404", description = "Task not found",
            )
        ]
    )
    fun deleteTask(@PathVariable id: Long)
}