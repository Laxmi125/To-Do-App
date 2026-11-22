package com.example.todo.service;

import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTasks(Boolean completed) {
        return completed == null ? taskRepository.findAll() : taskRepository.findByCompleted(completed);
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
    }

    public Task updateTask(Long id, Task updated) {
        Task existing = getTaskById(id);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setCompleted(updated.isCompleted());
        return taskRepository.save(existing);
    }

    public Task toggleStatus(Long id) {
        Task task = getTaskById(id);
        task.setCompleted(!task.isCompleted());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}
