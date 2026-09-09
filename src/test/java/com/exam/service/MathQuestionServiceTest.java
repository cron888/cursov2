package com.exam.service;

import com.exam.entity.Question;
import com.exam.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    @Mock
    QuestionRepository repository;

    @InjectMocks
    MathQuestionService service;

    @Test
    void getRandomQuestion_returnsQuestion() {
        Question q = new Question("2+2=?", "4");
        when(repository.getAll()).thenReturn(List.of(q));
        Question result = service.getRandomQuestion();
        assertNotNull(result);
        assertEquals("2+2=?", result.getQuestion());
    }

    @Test
    void getRandomQuestion_returnsNullWhenEmpty() {
        when(repository.getAll()).thenReturn(Collections.emptyList());
        Question result = service.getRandomQuestion();
        assertNull(result);
    }

    @Test
    void addString_callsRepository() {
        service.add("2+2=?", "4");
        verify(repository).add(argThat(q -> q.getQuestion().equals("2+2=?") && q.getAnswer().equals("4")));
    }

    @Test
    void addQuestion_callsRepository() {
        Question q = new Question("3*3=?", "9");
        service.add(q);
        verify(repository).add(q);
    }

    @Test
    void getAll_returnsAll() {
        Question q = new Question("3*3=?", "9");
        when(repository.getAll()).thenReturn(List.of(q));
        List<Question> result = service.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void remove_callsRepository() {
        Question q = new Question("2+2=?", "4");
        service.remove(q);
        verify(repository).remove(q);
    }
}
