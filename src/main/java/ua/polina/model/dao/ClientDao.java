package ua.polina.model.dao;

import ua.polina.model.entity.Client;

import java.util.Optional;

public interface ClientDao extends Dao<Client> {
    Optional<Client> findByUser(Long userId);
}
