package ua.polina.model.dao;

import ua.polina.model.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findByUsername(String username);
}
