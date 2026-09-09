package com.exam.repository;

import com.exam.entity.Question;
import java.util.List;

public interface QuestionRepository {
    void add(Question question);
    void remove(Question question);
    List<Question> getAll();
}
