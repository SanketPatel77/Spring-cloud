package com.sanket.quiz_service.controller;

import com.sanket.quiz_service.dto.QuizDTO;
import com.sanket.quiz_service.dto.Response;
import com.sanket.quiz_service.dto.SingleQuestionDTO;
import com.sanket.quiz_service.service.QuizService;
import com.sanket.quiz_service.service.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizServiceImpl quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
        return quizService.createQuiz(quizDTO);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<SingleQuestionDTO>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> getQuizQuestions(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateScore(id,responses);
    }
}
