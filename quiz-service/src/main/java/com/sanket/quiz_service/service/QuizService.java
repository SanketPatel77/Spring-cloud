package com.sanket.quiz_service.service;

import com.sanket.quiz_service.dto.QuizDTO;
import com.sanket.quiz_service.dto.Response;
import com.sanket.quiz_service.dto.SingleQuestionDTO;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {
    ResponseEntity<String> createQuiz(QuizDTO quizDTO);
    ResponseEntity<List<SingleQuestionDTO>> getQuizQuestions(Integer id);
    ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses);
}
