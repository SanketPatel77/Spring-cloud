package com.sanket.question_service.service;

import com.sanket.question_service.dto.QuestionDTO;
import com.sanket.question_service.dto.Response;
import com.sanket.question_service.dto.SingleQuestionDTO;
import com.sanket.question_service.model.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(QuestionDTO questionDTO);
    List<SingleQuestionDTO> getQuestionByCategory(String category);
    Question updateQuestion(Integer id, QuestionDTO questionDTO);
    int calculateScore(List<Response> response);
    List<Question> getAllQuestions();
    List<Integer> getQuestionsForQuiz(String category, int numQuestions);
    List<SingleQuestionDTO> getQuestionsById(List<Integer> list);
}
