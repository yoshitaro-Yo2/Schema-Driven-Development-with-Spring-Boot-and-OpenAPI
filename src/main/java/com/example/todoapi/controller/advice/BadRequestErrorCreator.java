package com.example.todoapi.controller.advice;

import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.InvalidParam;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

public class BadRequestErrorCreator {
    public static BadRequestError from(MethodArgumentNotValidException ex) {

        // ストリームで取ってきて、fieldError というオブジェクトが返ってくる
        // fieldError を invalidParam に map 上で変換する
        // 最後にコレクトでリスト変換
        List<InvalidParam> invalidParamList = ex.getFieldErrors()
                .stream()
                .map(fieldError -> {
                    var invalidParam = new InvalidParam();
                    invalidParam.setName(fieldError.getField());
                    invalidParam.setReason(fieldError.getDefaultMessage());
                    return invalidParam;
                })
                .collect(Collectors.toList());
        BadRequestError error = new BadRequestError();
        error.setInvalidParams(invalidParamList);

        return error;
    }
}
