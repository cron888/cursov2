package com.exam.controller;

import com.exam.entity.Question;
import com.exam.service.ExaminerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public ResponseEntity<List<Question>> getExamQuestions(@PathVariable int amount) {
        return ResponseEntity.ok(examinerService.getQuestions(amount));
    }
}
