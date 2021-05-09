package model.dao.mapper;

import model.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements ObjectMapper<Subject>{
    @Override
    public Subject extractFromResultSet(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getInt("id"));
        subject.setTitle(rs.getString("title"));
        return subject;
    }
}