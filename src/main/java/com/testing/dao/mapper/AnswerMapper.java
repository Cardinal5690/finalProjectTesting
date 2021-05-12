package com.testing.dao.mapper;

import com.testing.model.entity.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements ObjectMapper<Answer>{
    @Override
    public Answer extractFromResultSet(ResultSet rs) throws SQLException {
        Answer answer = new Answer();
        answer.setId(rs.getInt("id"));
        answer.setAnswerText(rs.getString("answer_text"));
        answer.setLetter(rs.getString("letter"));
        answer.setQuestion_id(rs.getInt("question_id"));
        return answer;
    }
}
