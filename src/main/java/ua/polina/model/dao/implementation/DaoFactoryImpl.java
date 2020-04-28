package ua.polina.model.dao.implementation;

import ua.polina.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactoryImpl extends DaoFactory {
    private DataSource dataSource = ConnectionPool.getDataSource();

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ClientDao createClientDao() {
        return new ClientDaoImpl(getConnection());
    }

    @Override
    public DescriptionDao createDescriptionDao() {
        return new DescriptionDaoImpl(getConnection());
    }

    @Override
    public RequestDao createRequestDao() {
        return new RequestDaoImpl(getConnection());
    }

    @Override
    public ReservationDao createReservationDao() {
        return new ReservationDaoImpl(getConnection());
    }

    @Override
    public RoomDao createRoomDao() {
        return new RoomDaoImpl(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    @Override
    public UserRoleDao createUserRoleDao() {
        return new UserRoleDaoImpl(getConnection());
    }
}
