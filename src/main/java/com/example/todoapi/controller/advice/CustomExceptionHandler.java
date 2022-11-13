package com.example.todoapi.controller.advice;

import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.ResourceNotFoundError;
import com.example.todoapi.service.task.TaskEntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// ExceptionHandlerで該当のExceptionが発生したときResponseStatusを返します
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    // Service クラスで発生した TaskEntityNotFoundException を catch する
    // 自分で定義した TaskEntityNotFoundException が発生した時のハンドラーメソッドであることを示す
    @ExceptionHandler(TaskEntityNotFoundException.class)
    public ResponseEntity<ResourceNotFoundError> handleTaskEntityNotFoundException (TaskEntityNotFoundException e) {
        ResourceNotFoundError error = new ResourceNotFoundError();
        error.setDetail(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        var error = BadRequestErrorCreator.from(ex);
        return ResponseEntity.badRequest().body(error);
    }
}
