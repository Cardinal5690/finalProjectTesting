package model.dao.daoImpl;

import model.dao.daoInterface.*;
import org.apache.log4j.Logger;

public abstract class DaoFactory {
    private final static Logger LOGGER = Logger.getLogger(DaoFactory.class);
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract TestDao createTestDao();
    public abstract SubjectDao createSubjectDao();
    public abstract TestResultDao createTestResultDao();
    public abstract QuestionDao createQuestionDao();
    public abstract AnswerDao createAnswerDao();

    public static DaoFactory getInstance (){
        if(daoFactory == null){
            synchronized (DaoFactory.class){
                if(daoFactory == null){
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        LOGGER.info("dao factory");
        return daoFactory;
    }
}
