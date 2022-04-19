package github.mewgrammer.taskbook.api.model;

import org.springframework.data.domain.Page;

import java.util.List;

public record Paginated<T>(List<T> content, long page, long size, long totalPages, long totalElements) {
    public static <U> Paginated<U> of(Page<U> page) {
        return new Paginated<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}