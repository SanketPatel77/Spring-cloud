package com.sanket.question_service.controller;

import com.sanket.question_service.dto.QuestionDTO;
import com.sanket.question_service.dto.Response;
import com.sanket.question_service.dto.SingleQuestionDTO;
import com.sanket.question_service.model.Question;
import com.sanket.question_service.service.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionService;

    @PostMapping("/create")
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionDTO questionDTO){
       Question question = questionService.createQuestion(questionDTO);
       return ResponseEntity.ok(question);
    }

    @GetMapping("/getQuestions/{category}")
    public ResponseEntity<List<SingleQuestionDTO>> getQuestionsByCategory(@PathVariable String category) {
        List<SingleQuestionDTO> questions = questionService.getQuestionByCategory(category);
        return questions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(questions);
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
       return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer id, @RequestBody QuestionDTO questionDTO) {
        Question updatedQuestion = questionService.updateQuestion(id, questionDTO);
        return ResponseEntity.ok(updatedQuestion);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numQuestions){
        List<Integer> questionIDs = questionService.getQuestionsForQuiz(category,numQuestions);
        if(questionIDs.isEmpty()){
            return new ResponseEntity<>(questionIDs, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questionIDs,HttpStatus.OK);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<SingleQuestionDTO>> getQuestionsById(@RequestBody List<Integer> questionIDs){
        List<SingleQuestionDTO> singleQuestionDTOList = questionService.getQuestionsById(questionIDs);
        return new ResponseEntity<>(singleQuestionDTOList,HttpStatus.OK);
    }


    @PostMapping("/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        int ans = questionService.calculateScore(responses);
        return ResponseEntity.ok(ans);
    }

}
