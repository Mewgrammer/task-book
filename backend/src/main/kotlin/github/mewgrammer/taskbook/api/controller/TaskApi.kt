package github.mewgrammer.taskbook.api.controller

import github.mewgrammer.taskbook.api.controller.model.UpdateTaskDto
import github.mewgrammer.taskbook.api.model.CreateTaskDto
import github.mewgrammer.taskbook.api.model.Paginated
import github.mewgrammer.taskbook.api.model.PaginationDto
import github.mewgrammer.taskbook.api.model.TaskDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@Tag(name = "task", description = "API to perform CRUD Operations on Tasks")
interface TaskApi {

    @Operation(summary = "Gets a page of Tasks")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Returns all Tasks for the requesting user",
            ),
            ApiResponse(
                responseCode = "400", description = "Invalid data supplied",
            ),
            ApiResponse(
                responseCode = "406", description = "Task already exists",
            )
        ]
    )
    fun getTasks(pagination: PaginationDto, auth: Authentication): Paginated<TaskDto>

    @Operation(summary = "Update a Task")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "The Task was created",
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
    fun addTask(createTaskData: CreateTaskDto): TaskDto

    @Operation(summary = "Updates a Task")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "The Task was updated",
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
        id: UUID,
        updateTaskData: UpdateTaskDto,
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
    fun deleteTask(id: UUID)
}