package com.exam.repository;

import com.exam.entity.Question;
import com.exam.repository.impl.InMemoryQuestionRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryQuestionRepositoryTest {

    @Test
    void addAndGetAll() {
        QuestionRepository repo = new InMemoryQuestionRepository();
        repo.add(new Question("Q", "A"));
        List<Question> all = repo.getAll();
        assertEquals(1, all.size());
        assertEquals("Q", all.get(0).getQuestion());
    }

    @Test
    void remove() {
        QuestionRepository repo = new InMemoryQuestionRepository();
        Question q = new Question("Q", "A");
        repo.add(q);
        repo.remove(q);
        assertTrue(repo.getAll().isEmpty());
    }

    @Test
    void getAll_returnsCopy() {
        QuestionRepository repo = new InMemoryQuestionRepository();
        repo.add(new Question("Q", "A"));
        List<Question> all = repo.getAll();
        all.clear();
        assertEquals(1, repo.getAll().size()); // original list unchanged
    }

    @Test
    void addMultipleQuestions() {
        QuestionRepository repo = new InMemoryQuestionRepository();
        repo.add(new Question("Q1", "A1"));
        repo.add(new Question("Q2", "A2"));
        assertEquals(2, repo.getAll().size());
    }
}
