package com.example.todoapi.repository.sample;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public class SampleRepository {

    public SampleRecord select() {
        return new SampleRecord("Hello World");
    }

}
