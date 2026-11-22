package com.example.todo.service;

import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    @Test
    void toggleStatus_flipsCompletedFlag() {
        Task task = new Task("Write report", "Q4");
        task.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        when(repository.save(any(Task.class))).thenAnswer(inv -> inv.getArgument(0));

        Task result = service.toggleStatus(1L);

        assertTrue(result.isCompleted());
    }

    @Test
    void getTaskById_throwsWhenMissing() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getTaskById(99L));
    }

    @Test
    void deleteTask_removesExistingTask() {
        Task task = new Task("Old", null);
        when(repository.findById(5L)).thenReturn(Optional.of(task));

        service.deleteTask(5L);

        verify(repository).delete(task);
    }
}
