package com.example.todoapi.controller.sample;

import com.example.todoapi.service.sample.SampleService;
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

    private final SampleService service = new SampleService();

    @GetMapping
    public SampleDTO index() {
        var entity = service.find();
        return new SampleDTO(entity.getContent(), LocalDateTime.now());
    }

}
