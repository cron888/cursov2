package com.exam.config;

import com.exam.entity.Question;
import com.exam.repository.QuestionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RepositoryConfig {

    @Bean("javaQuestions")
    public QuestionRepository javaQuestionsRepository() {
        return new InMemoryQuestionRepository();
    }

    @Bean("mathQuestions")
    public QuestionRepository mathQuestionsRepository() {
        return new InMemoryQuestionRepository();
    }

    private static class InMemoryQuestionRepository implements QuestionRepository {
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
}
