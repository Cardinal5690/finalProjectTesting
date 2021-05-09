package model.dao.conectionPool;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

public class ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    try {
                        Class.forName("org.postgresql.Driver");
                    } catch (ClassNotFoundException e) {
                        logger.error(e);
                    }
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:postgresql://localhost:5432/testing?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
                    ds.setUsername("postgres");
                    ds.setPassword("user");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    ds.setRemoveAbandonedTimeout(50);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
