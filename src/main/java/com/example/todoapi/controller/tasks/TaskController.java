package com.example.todoapi.controller.tasks;

import com.example.todoapi.controller.TasksApi;
import com.example.todoapi.model.TaskDTO;
import com.example.todoapi.model.TaskForm;
import com.example.todoapi.model.TaskLISTDTO;
import com.example.todoapi.service.task.TaskEntity;
import com.example.todoapi.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TaskController implements TasksApi {

    private final TaskService taskService;
    @Override
    public ResponseEntity<TaskDTO> showTask(Long taskId) {

        TaskEntity entity = taskService.find(taskId);
        TaskDTO dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskForm form) {

        TaskEntity entity = taskService.create(form.getTitle());
        TaskDTO dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return ResponseEntity
                .created(URI.create("/tasks/" + dto.getId()))
                .body(dto);
    }

    @Override
    public ResponseEntity<TaskLISTDTO> listTasks() {

        var entityList = taskService.find();
        TaskLISTDTO dto = new TaskLISTDTO();
        var dtoList = entityList.stream()
                        .map(taskEntity -> {
                            var taskDTO = new TaskDTO();
                            taskDTO.setId(taskEntity.getId());
                            taskDTO.setTitle(taskEntity.getTitle());
                            return taskDTO;
                        })
                        .collect(Collectors.toList());
        dto.setResults(dtoList);
        return ResponseEntity.ok(dto);
    }
}

