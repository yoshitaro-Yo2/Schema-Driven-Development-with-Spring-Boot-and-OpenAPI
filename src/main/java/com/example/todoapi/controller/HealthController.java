package com.example.todoapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController implements HealthApi {

    // インターフェイスでは デフォルト実装されているメソッド。まだ実装されていないというモック
    // return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    @Override
    public ResponseEntity<Void> healthGet() {
        return ResponseEntity.ok().build();
    }
}
