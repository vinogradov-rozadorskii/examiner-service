package org.skypro.examinerservice.service;

import org.junit.jupiter.api.Test;
import org.skypro.examinerservice.domain.Question;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private final JavaQuestionService questionService = new JavaQuestionService();

    @Test
    void addQuestion() {
        Question question = new Question("Вопрос 1", "Ответ 1");

        Question result = questionService.add(question);

        assertEquals(question, result);
        assertTrue(questionService.getAll().contains(question));
    }

    @Test
    void addQuestionByStrings() {
        Question result = questionService.add("Вопрос 1", "Ответ 1");

        assertEquals(new Question("Вопрос 1", "Ответ 1"), result);
        assertTrue(questionService.getAll().contains(result));
    }

    @Test
    void removeQuestion() {
        Question question = new Question("Вопрос 1", "Ответ 1");
        questionService.add(question);

        Question result = questionService.remove(question);

        assertEquals(question, result);
        assertFalse(questionService.getAll().contains(question));
    }

    @Test
    void removeFromEmptyCollection() {
        Question question = new Question("Вопрос 1", "Ответ 1");

        Question result = questionService.remove(question);

        assertNull(result);
    }

    @Test
    void getAllQuestions() {
        Question first = new Question("Вопрос 1", "Ответ 1");
        Question second = new Question("Вопрос 2", "Ответ 2");

        questionService.add(first);
        questionService.add(second);

        Collection<Question> result = questionService.getAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(first));
        assertTrue(result.contains(second));
    }

    @Test
    void getRandomQuestion() {
        Question question = new Question("Вопрос 1", "Ответ 1");
        questionService.add(question);

        Question result = questionService.getRandomQuestion();

        assertEquals(question, result);
    }

    @Test
    void getRandomQuestionFromEmptyCollection() {
        assertNull(questionService.getRandomQuestion());
    }
    @Test
    void addDuplicateQuestion() {
        Question question = new Question("Вопрос 1", "Ответ 1");

        questionService.add(question);
        questionService.add(question);

        Collection<Question> result = questionService.getAll();

        assertEquals(1, result.size());
        assertTrue(result.contains(question));
    }
}