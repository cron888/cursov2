package com.exam.repository.impl;

import com.exam.entity.Question;
import com.exam.repository.QuestionRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryQuestionRepository implements QuestionRepository {
    private final List<Question> questions = new ArrayList<>();

    @Override
    public void add(Question question) {
        questions.add(question);
    }

    @Override
    public void remove(Question question) {
        questions.remove(question);
    }

    @Override
    public List<Question> getAll() {
        return new ArrayList<>(questions);
    }
}
