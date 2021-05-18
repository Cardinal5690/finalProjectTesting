package com.testing.model.service;

import com.testing.model.entity.Question;
import com.testing.model.entity.Test;

import java.util.List;

public interface QuestionService extends GeneralService<Question>{
    List<Question> getQuestionByTestId(int testId);
    void setQuestion(Test test);
    List<Question> getAllQuestionsByTestName (String testName);
}
