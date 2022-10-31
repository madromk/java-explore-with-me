package ru.practicum.explore_with_me.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore_with_me.category.dto.CategoryDto;
import ru.practicum.explore_with_me.category.dto.NewCategoryDto;
import ru.practicum.explore_with_me.category.service.CategoryService;
import ru.practicum.explore_with_me.exception.ObjectNotFoundException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/admin/categories")
@Validated
public class AdminCategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public CategoryDto createCategory(@RequestBody NewCategoryDto categoryDto) {
        log.info("Creating category {}", categoryDto);
        return categoryService.createCategory(categoryDto);
    }

    @PatchMapping
    public CategoryDto changeCategory(@RequestBody NewCategoryDto categoryDto) {
        log.info("Changing category {}", categoryDto);
        return categoryService.changeCategory(categoryDto);
    }

    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable long catId) {
        log.info("Deleting category {}", catId);
        categoryService.deleteCategory(catId);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleNegativeRequest(final MethodArgumentNotValidException e) {
        return Map.of(
                "error", "",
                "errorMessage", e.getMessage(),
                "reason", "For the requested operation the conditions are not met.",
                "status", "BAD_REQUEST",
                "timestamp", LocalDateTime.now().toString()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleConflict(final SQLException e) {
        return Map.of(
                "status", "CONFLICT",
                "reason", "Integrity constraint has been violated",
                "message", e.getMessage(),
                "timestamp", LocalDateTime.now().toString()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleObjectNotFound(final ObjectNotFoundException e) {
        return Map.of(
                "status", "NOT_FOUND",
                "reason", "The category object was not found.",
                "message", e.getMessage(),
                "timestamp", LocalDateTime.now().toString()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleServerError(final Exception e) {
        return Map.of(
                "status", "INTERNAL_SERVER_ERROR",
                "reason", "Error occurred",
                "message", e.getMessage(),
                "timestamp", LocalDateTime.now().toString()
        );
    }
}
