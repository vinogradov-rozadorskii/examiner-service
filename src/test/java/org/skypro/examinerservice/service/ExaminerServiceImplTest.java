package org.skypro.examinerservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.examinerservice.domain.Question;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestionsReturnsRequiredAmount() {
        Question first = new Question("Вопрос 1", "Ответ 1");
        Question second = new Question("Вопрос 2", "Ответ 2");

        when(questionService.getAll()).thenReturn(List.of(first, second));
        when(questionService.getRandomQuestion())
                .thenReturn(first)
                .thenReturn(second);

        var result = examinerService.getQuestions(2);

        assertEquals(2, result.size());
        assertTrue(result.contains(first));
        assertTrue(result.contains(second));
    }

    @Test
    void getQuestionsThrowsExceptionWhenAmountIsTooLarge() {
        Question question = new Question("Вопрос 1", "Ответ 1");

        when(questionService.getAll()).thenReturn(List.of(question));

        assertThrows(
                ResponseStatusException.class,
                () -> examinerService.getQuestions(2)
        );
    }

    @Test
    void getQuestionsReturnsEmptyCollectionWhenAmountIsZero() {
        Question question = new Question("Вопрос 1", "Ответ 1");

        when(questionService.getAll()).thenReturn(List.of(question));

        var result = examinerService.getQuestions(0);

        assertTrue(result.isEmpty());
    }

    @Test
    void getQuestionsDoesNotReturnDuplicates() {
        Question first = new Question("Вопрос 1", "Ответ 1");
        Question second = new Question("Вопрос 2", "Ответ 2");

        when(questionService.getAll()).thenReturn(List.of(first, second));
        when(questionService.getRandomQuestion())
                .thenReturn(first)
                .thenReturn(first)
                .thenReturn(second);

        var result = examinerService.getQuestions(2);

        assertEquals(2, result.size());
        assertTrue(result.contains(first));
        assertTrue(result.contains(second));
    }
}