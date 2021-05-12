package com.testing.dao.mapper;

import com.testing.model.entity.Test;
import com.testing.model.entity.type.Complexity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMapper implements ObjectMapper<Test> {
    @Override
    public Test extractFromResultSet(ResultSet rs) throws SQLException {
        Test test = new Test();
        test.setId(rs.getInt("id"));
        test.setTestName(rs.getString("test_name"));
        test.setComplexity(Enum.valueOf(Complexity.class, rs.getString("complexity")));
        test.setTime(rs.getInt("time"));
        test.setSubject_id(rs.getInt("subject_id"));
        return test;
    }
}
