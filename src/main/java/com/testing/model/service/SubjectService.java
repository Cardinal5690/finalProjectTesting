package com.testing.model.service;

import com.testing.model.entity.Subject;

import java.util.List;

public interface SubjectService extends GeneralService<Subject>{
    List<Subject> findAllByLocale (String locale);
    Subject findByTitle(String title);
}
