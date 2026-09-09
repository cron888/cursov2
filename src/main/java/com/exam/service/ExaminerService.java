package com.exam.service;

import com.exam.entity.Question;
import java.util.List;

public interface ExaminerService {
    List<Question> getQuestions(int amount);
}
