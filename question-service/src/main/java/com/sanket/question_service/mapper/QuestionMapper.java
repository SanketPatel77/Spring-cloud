package com.sanket.question_service.mapper;

import com.sanket.question_service.dto.QuestionDTO;
import com.sanket.question_service.dto.SingleQuestionDTO;
import com.sanket.question_service.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    // Entity to DTO
    QuestionDTO toDTO(Question question);

    // DTO to Entity
    Question toEntity(QuestionDTO questionDTO);

    // Limited mapping for only question and options
    SingleQuestionDTO toSingleQuestionDTO(Question question);

    // Update existing entity fields from DTO (Important!)
    void updateEntity(@MappingTarget Question existingQuestion, QuestionDTO questionDTO);
}

