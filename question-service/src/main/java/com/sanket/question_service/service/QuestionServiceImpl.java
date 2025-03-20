package com.sanket.question_service.service;

import com.sanket.question_service.dto.QuestionDTO;
import com.sanket.question_service.dto.Response;
import com.sanket.question_service.dto.SingleQuestionDTO;
import com.sanket.question_service.mapper.QuestionMapper;
import com.sanket.question_service.model.Question;
import com.sanket.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService{

    private QuestionMapper questionMapper;
    private QuestionRepository questionRepository;

    @Autowired
    QuestionServiceImpl(QuestionMapper questionMapper, QuestionRepository questionRepository){
        this.questionMapper = questionMapper;
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(QuestionDTO questionDTO) {
        Question question = questionMapper.toEntity(questionDTO);
        questionRepository.save(question);
        return question;
    }

    @Override
    public List<SingleQuestionDTO> getQuestionByCategory(String category) {
        List<Question> questions = questionRepository.findByCategory(category);
        return questions.stream()
                .map(questionMapper::toSingleQuestionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Question updateQuestion(Integer id, QuestionDTO questionDTO) {
        Optional<Question> question = questionRepository.findById(id);

        if(question.isPresent()){
            Question existingQuestion = question.get();
            questionMapper.updateEntity(existingQuestion, questionDTO);
            return questionRepository.save(existingQuestion);
        }else{
            throw new RuntimeException("Question with ID " + id + " not found");
        }
    }

    @Override
    public int calculateScore(List<Response> responses) {
        int score = 0;
        for(Response response : responses){
            Optional<Question> existingQuestion = questionRepository.findById(response.getId());
            if(existingQuestion.isPresent()){
                Question question = existingQuestion.get();
                if(question.getRightAnswer().equalsIgnoreCase(response.getAnswer())){
                    score++;
                }
            }
        }
        return score;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }


    public List<Integer> getQuestionsForQuiz(String category, int numQuestions) {
        return questionRepository.findRandomQuestionsByCategory(category,numQuestions);
    }

    @Override
    public List<SingleQuestionDTO> getQuestionsById(List<Integer> questionIDs) {
        List<Question> questions = questionRepository.findAllById(questionIDs);

        return questions.stream()
                .map(questionMapper::toSingleQuestionDTO)
                .collect(Collectors.toList());
    }

}
