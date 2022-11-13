package com.example.todoapi.controller.advice;

import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.InvalidParam;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BadRequestErrorCreator {
    public static BadRequestError from(MethodArgumentNotValidException ex) {

        // ストリームで取ってきて、fieldError というオブジェクトが返ってくる
        // fieldError を invalidParam に map 上で変換する
        // 最後にコレクトでリスト変換
//        List<InvalidParam> invalidParamList = ex.getFieldErrors()
//                .stream()
//                .map(fieldError -> {
//                    var invalidParam = new InvalidParam();
//                    invalidParam.setName(fieldError.getField());
//                    invalidParam.setReason(fieldError.getDefaultMessage());
//                    return invalidParam;
//                })
//                .collect(Collectors.toList());

        // map 内の処理を抽出
//        List<InvalidParam> invalidParamList = ex.getFieldErrors()
//                .stream()
//                .map(fieldError -> createInvalidParam(fieldError))
//                .collect(Collectors.toList());

        // 引数が一つの変数の場合メソッド参照で置き換えが可能になる。
//        List<InvalidParam> invalidParamList = ex.getFieldErrors()
//                .stream()
//                .map(BadRequestErrorCreator::createInvalidParam)
//                .collect(Collectors.toList());

        List<InvalidParam> invalidParamList = createInvalidParamList(ex);

        BadRequestError error = new BadRequestError();
        error.setInvalidParams(invalidParamList);

        return error;
    }

    private static List<InvalidParam> createInvalidParamList(MethodArgumentNotValidException ex) {
        return ex.getFieldErrors()
                .stream()
                .map(BadRequestErrorCreator::createInvalidParam)
                .collect(Collectors.toList());
    }

    private static InvalidParam createInvalidParam(FieldError fieldError) {
        var invalidParam = new InvalidParam();
        invalidParam.setName(fieldError.getField());
        invalidParam.setReason(fieldError.getDefaultMessage());
        return invalidParam;
    }
}
