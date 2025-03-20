package com.sanket.quiz_service.service;

import com.sanket.quiz_service.dto.QuizDTO;
import com.sanket.quiz_service.dto.Response;
import com.sanket.quiz_service.dto.SingleQuestionDTO;
import com.sanket.quiz_service.feign.QuizInterface;
import com.sanket.quiz_service.model.Quiz;
import com.sanket.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    @Override
    public ResponseEntity<String> createQuiz(QuizDTO quizDTO) {
        ResponseEntity<List<Integer>> response = quizInterface.getQuestionsForQuiz(quizDTO.getCategory(), quizDTO.getNumQuestions());

        if (response.getBody() == null || response.getBody().isEmpty()) {
            return new ResponseEntity<>("Failed to fetch questions", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<Integer> questionIDs = response.getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(quizDTO.getTitle());
        quiz.setQuestionIDs(questionIDs);
        quizRepository.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<SingleQuestionDTO>> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found for id: " + id));
        List<Integer> questionIds = quiz.getQuestionIDs();
        if (questionIds == null || questionIds.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return quizInterface.getQuestionsById(questionIds);

    }

    @Override
    public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {
        if (responses == null || responses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return quizInterface.getScore(responses);
    }
}

