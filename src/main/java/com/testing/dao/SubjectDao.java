package com.testing.dao;

import com.testing.model.entity.Subject;

import java.util.List;

public interface SubjectDao extends GenericDao<Subject> {
    List<Subject> findAllByUserId(int user_id);
}
