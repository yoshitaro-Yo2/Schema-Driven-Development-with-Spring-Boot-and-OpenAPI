package com.example.todoapi.controller.tasks;

import com.example.todoapi.controller.TasksApi;
import com.example.todoapi.model.PageDTO;
import com.example.todoapi.model.TaskDTO;
import com.example.todoapi.model.TaskForm;
import com.example.todoapi.model.TaskLISTDTO;
import com.example.todoapi.service.task.TaskEntity;
import com.example.todoapi.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TaskController implements TasksApi {

    private final TaskService taskService;
    @Override
    public ResponseEntity<TaskDTO> showTask(Long taskId) {

        TaskEntity entity = taskService.find(taskId);
        TaskDTO dto = toTaskDto(entity);

        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskForm form) {

        TaskEntity entity = taskService.create(form.getTitle());
        TaskDTO dto = toTaskDto(entity);
        return ResponseEntity
                .created(URI.create("/tasks/" + dto.getId()))
                .body(dto);
    }

    @Override
    public ResponseEntity<TaskLISTDTO> listTasks(Integer limit, Long offset) {
        var entityList = taskService.find(limit, offset);
        var dto = new TaskLISTDTO();
        var pageDTO = new PageDTO();
        var dtoList = entityList.stream()
                        .map(TaskController::toTaskDto)
                        .collect(Collectors.toList());

        pageDTO.setLimit(limit);
        pageDTO.setOffset(offset);
        pageDTO.setSize(dtoList.size());

        dto.setResults(dtoList);
        dto.setPage(pageDTO);
        return ResponseEntity.ok(dto);
    }

    private static TaskDTO toTaskDto(TaskEntity taskEntity) {
        var taskDTO = new TaskDTO();
        taskDTO.setId(taskEntity.getId());
        taskDTO.setTitle(taskEntity.getTitle());
        return taskDTO;
    }
}

