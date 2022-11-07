package com.example.todoapi.repository.sample;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

// MyBatis のマッパ宣言 Bean登録もされる
@Mapper
public interface SampleRepository {
    @Select("SELECT * FROM samples ORDER BY id LIMIT 1")
    SampleRecord select();
}
