package com.testing.dao;

import com.testing.model.entity.Test;

import java.util.List;

public interface TestDao extends GenericDao<Test> {
    List<Test> getAllTestBySubjectId(int subjectId);
    List<Test> getBySubjectTitle(String title);
    Test getByName (String name);
}
