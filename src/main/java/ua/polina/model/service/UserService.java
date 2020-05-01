package ua.polina.model.service;

import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.UserDao;
import ua.polina.model.entity.User;

import java.util.Optional;

public class UserService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<User> getUserByUsername(String username){
        try(UserDao userDao = daoFactory.createUserDao()){
            return userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
