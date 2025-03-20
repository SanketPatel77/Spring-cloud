package com.sanket.quiz_service.dto;

import lombok.Data;

@Data
public class SingleQuestionDTO {
    private Integer id;
    private  String questionTitle;
    private  String option1;
    private  String option2;
    private  String option3;
    private  String option4;

}
