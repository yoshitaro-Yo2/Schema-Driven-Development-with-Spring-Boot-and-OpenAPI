package com.example.todoapi.controller.sample;

import com.example.todoapi.service.sample.SampleEntity;
import com.example.todoapi.service.sample.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

// 合成アノテーション @ResponseBody
// ハンドラーメソッドの戻り値がレスポンスボディに書き出されるアノテーション
// Postman でレスポンスボディ見れるよになる
@RestController
@RequestMapping("/samples")
// インジェクションの受け口としてコンストラクタが必要 Lombok で作成
// @RequiredArgsConstructor … finalフィールドに対し初期化値を引数にとるコンストラクタ生成
@RequiredArgsConstructor
public class SampleController {

    private final SampleService service;

    @GetMapping
    public SampleDTO index() {
        SampleEntity entity = service.find();
        return new SampleDTO(entity.getContent(), LocalDateTime.now());
    }

}
