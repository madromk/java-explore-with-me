package ru.practicum.explore_with_me.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore_with_me.exception.InputDataException;
import ru.practicum.explore_with_me.exception.ObjectNotFoundException;
import ru.practicum.explore_with_me.user.dto.UserDto;
import ru.practicum.explore_with_me.user.service.UserService;

import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/admin/users")
@Slf4j
@RequiredArgsConstructor
@Validated
public class AdminUserController {

    private static final String FROM = "0";
    private static final String SIZE = "10";
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam List<Long> ids, @RequestParam(defaultValue = FROM) int from,
                                     @RequestParam(defaultValue = SIZE) int size) {
        log.info("Get users");
        return userService.getAllUsers(ids, from, size);
    }

    @PostMapping
    public UserDto addUser(@Valid @RequestBody UserDto userDto) {
        log.info("Creating user {}", userDto);
        return userService.addUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable long userId) {
        log.info("Deleting user {}", userId);
        userService.deleteUserById(userId);
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
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleNegativeInputData(final InputDataException e) {
        return Map.of(
                "error", "",
                "errorMessage", e.getMessage(),
                "reason", "For the requested operation the conditions are not met.",
                "status", "FORBIDDEN",
                "timestamp", LocalDateTime.now().toString()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleObjectNotFound(final ObjectNotFoundException e) {
        return Map.of(
                "status", "NOT_FOUND",
                "reason", "The user object was not found.",
                "message", e.getMessage(),
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
