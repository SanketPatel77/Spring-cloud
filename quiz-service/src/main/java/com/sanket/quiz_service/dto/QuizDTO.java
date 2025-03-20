package com.sanket.quiz_service.dto;

import lombok.Data;

@Data
public class QuizDTO {
    private String category;
    private Integer numQuestions;
    private String title;
}
