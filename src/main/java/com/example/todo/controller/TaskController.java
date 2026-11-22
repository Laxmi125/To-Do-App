package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
    }

    @GetMapping
    public List<Task> getAll(@RequestParam(required = false) Boolean completed) {
        return taskService.getAllTasks(completed);
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @Valid @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @PatchMapping("/{id}/toggle")
    public Task toggle(@PathVariable Long id) {
        return taskService.toggleStatus(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
