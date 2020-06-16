package ua.polina.model.dao.mapper;

import ua.polina.model.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ClientMapper implements Mapper<Client> {
    @Override
    public Client resultSetToEntity(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getLong("id"));
        client.setFirstName(resultSet.getString("first_name"));
        client.setFirstNameUk(resultSet.getString("first_name_uk"));
        client.setMiddleName(resultSet.getString("middle_name"));
        client.setMiddleNameUk(resultSet.getString("middle_name_uk"));
        client.setLastName(resultSet.getString("last_name"));
        client.setLastNameUk(resultSet.getString("last_name_uk"));
        client.setPassport(resultSet.getString("passport"));
        client.setBirthday(resultSet.getDate("birthday").toLocalDate());
        return client;
    }

    @Override
    public Client makeUnique(Map<Long, Client> cache, Client entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
