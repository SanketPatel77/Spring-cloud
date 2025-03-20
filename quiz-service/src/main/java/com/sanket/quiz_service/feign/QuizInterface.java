package com.sanket.quiz_service.feign;

import com.sanket.quiz_service.dto.Response;
import com.sanket.quiz_service.dto.SingleQuestionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question/generate")
    ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numQuestions);

    @PostMapping("question/getQuestions")
    ResponseEntity<List<SingleQuestionDTO>> getQuestionsById(@RequestBody List<Integer> questionIDs);


    @PostMapping("question/getscore")
    ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
