package ua.polina.model.dao;

import ua.polina.model.dao.implementation.DaoFactoryImpl;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract ClientDao createClientDao();

    public abstract DescriptionDao createDescriptionDao();

    public abstract RequestDao createRequestDao();

    public abstract ReservationDao createReservationDao();

    public abstract RoomDao createRoomDao();

    public abstract UserDao createUserDao();

    public abstract UserRoleDao createUserRoleDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new DaoFactoryImpl();
                }
            }
        }
        return daoFactory;
    }
}
