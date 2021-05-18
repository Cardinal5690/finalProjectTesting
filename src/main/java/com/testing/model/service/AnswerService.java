package com.testing.model.service;

import com.testing.model.entity.Answer;
import com.testing.model.entity.Question;

public interface AnswerService extends GeneralService<Answer>{
    void setAnswer (Question question);
}
