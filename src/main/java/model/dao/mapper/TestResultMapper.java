package model.dao.mapper;

import model.entity.TestResult;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestResultMapper implements ObjectMapper<TestResult>{
    @Override
    public TestResult extractFromResultSet(ResultSet rs) throws SQLException {
        TestResult testResult = new TestResult();
        testResult.setId(rs.getInt("id"));
        testResult.setResult(rs.getInt("result"));
        testResult.setUser_id(rs.getInt("user_id"));
        testResult.setTest_id(rs.getInt("test_id"));
        return testResult;
    }
}
