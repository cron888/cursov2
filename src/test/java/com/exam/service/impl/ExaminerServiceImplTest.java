package com.exam.service.impl;

import com.exam.entity.Question;
import com.exam.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    private QuestionService javaService = mock(QuestionService.class);
    private QuestionService mathService = mock(QuestionService.class);
    private ExaminerServiceImpl service = new ExaminerServiceImpl(javaService, mathService);

    @Test
    void getQuestions_returnsQuestions() {
        Question q1 = new Question("Java Q1", "A1");
        Question q2 = new Question("Math Q1", "A2");
        
        doReturn(List.of(q1)).when(javaService).getAll();
        doReturn(List.of(q2)).when(mathService).getAll();

        List<Question> result = service.getQuestions(2);
        assertEquals(2, result.size());
    }

    @Test
    void getQuestions_throwsBadRequestWhenTooMany() {
        doReturn(List.of()).when(javaService).getAll();
        doReturn(List.of()).when(mathService).getAll();

        assertThrows(ResponseStatusException.class, () -> service.getQuestions(1));
    }

    @Test
    void getQuestions_returnsLessWhenAvailable() {
        Question q1 = new Question("Java Q", "A");
        doReturn(List.of(q1)).when(javaService).getAll();
        doReturn(List.of()).when(mathService).getAll();

        List<Question> result = service.getQuestions(1);
        assertEquals(1, result.size());
    }

    @Test
    void getQuestions_returnsEmptyWhenRequestedZero() {
        doReturn(List.of()).when(javaService).getAll();
        doReturn(List.of()).when(mathService).getAll();

        List<Question> result = service.getQuestions(0);
        assertTrue(result.isEmpty());
    }

    @Test
    void getQuestions_filtersDuplicateQuestions() {
        Question q1 = new Question("Q", "A");
        Question q2 = new Question("Q", "A"); // same content, will be deduplicated
        doReturn(List.of(q1)).when(javaService).getAll();
        doReturn(List.of(q2)).when(mathService).getAll();

        List<Question> result = service.getQuestions(1);
        assertEquals(1, result.size()); // duplicates filtered to 1
    }
}
