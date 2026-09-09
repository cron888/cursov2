package com.exam.service.impl;

import com.exam.entity.Question;
import com.exam.service.QuestionService;
import com.exam.service.ExaminerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(
            @Qualifier("javaQuestionService") QuestionService javaQuestionService,
            @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public List<Question> getQuestions(int amount) {
        Set<Question> unique = new LinkedHashSet<>();
        javaQuestionService.getAll().forEach(unique::add);
        mathQuestionService.getAll().forEach(unique::add);

        if (amount > unique.size()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Requested " + amount + " questions but only " + unique.size() + " available"
            );
        }

        List<Question> all = new ArrayList<>(unique);
        Collections.shuffle(all, new Random());
        return all.subList(0, amount);
    }
}
