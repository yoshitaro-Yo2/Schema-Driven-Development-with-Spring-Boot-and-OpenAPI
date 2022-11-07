package com.example.todoapi.controller.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

// 合成アノテーション @ResponseBody
// ハンドラーメソッドの戻り値がレスポンスボディに書き出されるアノテーション
// Postman でレスポンスボディ見れるよになる
@RestController
@RequestMapping("/samples")
public class SampleController {

    @GetMapping
    public SampleDTO index() {
        return new SampleDTO("ok", LocalDateTime.now());
    }

}
