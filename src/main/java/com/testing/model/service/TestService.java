package com.testing.model.service;

import com.testing.model.entity.Test;

import java.util.List;

public interface TestService extends GeneralService<Test>{
    List<Test> getBySubjectTitle(String title);
    Test getByName (String name);
}
