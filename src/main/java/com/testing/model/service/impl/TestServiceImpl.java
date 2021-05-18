package com.testing.model.service.impl;

import com.testing.dao.TestDao;
import com.testing.dao.impl.DaoFactory;
import com.testing.model.entity.Test;
import com.testing.model.service.TestService;
import org.apache.log4j.Logger;

import java.util.List;

public class TestServiceImpl implements TestService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    TestDao testDao = daoFactory.createTestDao();
    private static final Logger LOGGER = Logger.getLogger(TestServiceImpl.class);

    @Override
    public Test create(Test entity) {
        LOGGER.info("The service creates a test");
        return testDao.create(entity);
    }

    @Override
    public Test findById(int id) {
        LOGGER.info("The service searches for a test by id");
        return testDao.findById(id);
    }

    @Override
    public List<Test> findAll() {
        LOGGER.info("The service looks for all tests");
        return testDao.findAll();
    }

    @Override
    public void update(Test entity) {
        LOGGER.info("The service updates test");
        testDao.update(entity);
    }

    @Override
    public void delete(int id) {
        LOGGER.info("The service deletes a test");
        testDao.delete(id);
    }

    @Override
    public List<Test> getBySubjectTitle(String title) {
        LOGGER.info("The service sets all the tests for the subject");
        return testDao.getBySubjectTitle(title);
    }

    @Override
    public Test getByName(String name) {
        LOGGER.info("The service searches for a test by name");
        return testDao.getByName(name);
    }
}
