package ua.polina.model.dao;

import ua.polina.model.entity.UserRole;

import java.util.List;

public interface UserRoleDao extends Dao<UserRole> {
    List<UserRole> findRolesByUser(Long userId);
}
