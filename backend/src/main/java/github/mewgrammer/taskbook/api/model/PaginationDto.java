package github.mewgrammer.taskbook.api.model;

import lombok.Data;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

// Must be class for spring-doc @ParameterObject to work
@SuppressWarnings("ClassCanBeRecord")
@Data
public class PaginationDto {
    @PositiveOrZero
    private final int page;
    @Positive
    private final int size;
}
