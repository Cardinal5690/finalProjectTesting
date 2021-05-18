package com.testing.model.service.impl;

import com.testing.dao.SubjectDao;
import com.testing.dao.impl.DaoFactory;
import com.testing.model.entity.Subject;
import com.testing.model.service.SubjectService;
import org.apache.log4j.Logger;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    SubjectDao subjectDao = daoFactory.createSubjectDao();
    private final static Logger LOGGER = Logger.getLogger(SubjectServiceImpl.class);

    @Override
    public Subject create(Subject entity) {
       LOGGER.info("The service creates a subject");
        return subjectDao.create(entity);
    }

    @Override
    public Subject findById(int id) {
        LOGGER.info("The service searches for a subject by id");
        return subjectDao.findById(id);
    }

    @Override
    public List<Subject> findAll() {
        LOGGER.info("Service looks for all subjects");
        return subjectDao.findAll();
    }

    @Override
    public void update(Subject entity) {
        LOGGER.info("The service updates a subject");
        subjectDao.update(entity);
    }

    @Override
    public void delete(int id) {
        LOGGER.info("The service deletes a subject");
        subjectDao.delete(id);
    }

}
