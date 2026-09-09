package com.exam.service;

import com.exam.entity.Question;
import com.exam.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service("mathQuestionService")
public class MathQuestionService implements QuestionService {
    private final QuestionRepository repository;
    private final Random random = new Random();

    public MathQuestionService(@Qualifier("mathQuestions") QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(String question, String answer) {
        repository.add(new Question(question, answer));
    }

    @Override
    public void add(Question question) {
        repository.add(question);
    }

    @Override
    public void remove(Question question) {
        repository.remove(question);
    }

    @Override
    public List<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> all = repository.getAll();
        if (all.isEmpty()) {
            return null;
        }
        return all.get(random.nextInt(all.size()));
    }
}
