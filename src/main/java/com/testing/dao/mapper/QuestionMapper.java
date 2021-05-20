package com.testing.dao.mapper;

import com.testing.model.entity.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements ObjectMapper<Question>{
    @Override
    public Question extractFromResultSet(ResultSet rs) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setQuestionText(rs.getString("question_text"));
        question.setCorrectAnswer(rs.getString("correct_answer"));
        question.setTestId(rs.getInt("test_id"));
        return question;
    }
}
