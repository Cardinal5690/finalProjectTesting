package com.testing.model.service;

import com.testing.model.entity.Question;

import java.util.List;

public interface QuestionService extends GeneralService<Question>{
    List<Question> getAllQuestionsByTestName (String testName);
}
