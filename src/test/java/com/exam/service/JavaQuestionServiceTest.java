package com.exam.service;

import com.exam.entity.Question;
import com.exam.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    QuestionRepository repository;

    @InjectMocks
    JavaQuestionService service;

    @Test
    void getRandomQuestion_returnsQuestion() {
        Question q = new Question("Q1", "A1");
        when(repository.getAll()).thenReturn(Arrays.asList(q));
        Question result = service.getRandomQuestion();
        assertNotNull(result);
        assertEquals("Q1", result.getQuestion());
    }

    @Test
    void getRandomQuestion_returnsNullWhenEmpty() {
        when(repository.getAll()).thenReturn(Collections.emptyList());
        Question result = service.getRandomQuestion();
        assertNull(result);
    }

    @Test
    void addString_callsRepository() {
        service.add("Q", "A");
        verify(repository).add(argThat(q -> q.getQuestion().equals("Q") && q.getAnswer().equals("A")));
    }

    @Test
    void addQuestion_callsRepository() {
        Question q = new Question("Q", "A");
        service.add(q);
        verify(repository).add(q);
    }

    @Test
    void getAll_returnsAll() {
        Question q = new Question("Q1", "A1");
        when(repository.getAll()).thenReturn(List.of(q));
        List<Question> result = service.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void remove_callsRepository() {
        Question q = new Question("Q1", "A1");
        service.remove(q);
        verify(repository).remove(q);
    }
}
