package com.exam.service;

import com.exam.entity.Question;
import java.util.List;

public interface QuestionService {
    void add(String question, String answer);
    void add(Question question);
    void remove(Question question);
    List<Question> getAll();
    Question getRandomQuestion();
}
