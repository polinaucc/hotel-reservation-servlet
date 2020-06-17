package ua.polina.model.dao.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TransactionalDaoFactory extends DaoFactoryImpl {
    private Connection connection;

    @Override
    public Connection getConnection(){
        System.out.println("In my method");
        if (Objects.isNull(connection)) {
            try {
                connection =  ConnectionPool.getDataSource().getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commitTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public void rollbackTransaction() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
