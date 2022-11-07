package com.example.todoapi.controller.sample;

import lombok.Value;

import java.time.LocalDateTime;

// クラスに@Valueアノテーションを付与することで、対象クラスをImmutableの状態にする
// クラスおよび各フィールドは final、各フィールドは自動で可視性が private DDD の値オブジェクトみたい
// @Getter, @ToString, @EqualsAndHashCode, @AllArgsConstructorを付与したのと同じ状態
//
@Value
public class SampleDTO {
    String content;
    LocalDateTime timestamp;
}
