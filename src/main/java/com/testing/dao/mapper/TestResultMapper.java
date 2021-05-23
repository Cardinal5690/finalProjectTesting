package com.testing.dao.mapper;

import com.testing.model.entity.TestResult;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestResultMapper implements ObjectMapper<TestResult>{
    @Override
    public TestResult extractFromResultSet(ResultSet rs) throws SQLException {
        TestResult testResult = new TestResult();
        testResult.setId(rs.getInt("id"));
        testResult.setResult(rs.getInt("result"));
        testResult.setUserId(rs.getInt("user_id"));
        testResult.setTestId(rs.getInt("test_id"));
        return testResult;
    }
}
