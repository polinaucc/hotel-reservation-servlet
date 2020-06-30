package ua.polina.model.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.polina.model.dao.ClientDao;
import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.UserDao;
import ua.polina.model.dao.UserRoleDao;
import ua.polina.model.dao.implementation.TransactionalDaoFactory;
import ua.polina.model.dto.SignUpDto;
import ua.polina.model.entity.Client;
import ua.polina.model.entity.Role;
import ua.polina.model.entity.User;
import ua.polina.model.entity.UserRole;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ClientService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private TransactionalDaoFactory transactionalDaoFactory;

    private final Logger LOGGER = LogManager.getLogger(ClientService.class);

    public ClientService() {
        transactionalDaoFactory = new TransactionalDaoFactory();
    }

    public void saveNewClient(SignUpDto signUpDto) throws Exception {
        ClientDao clientDao = transactionalDaoFactory.createClientDao();
        UserDao userDao = transactionalDaoFactory.createUserDao();
        UserRoleDao userRoleDao = transactionalDaoFactory.createUserRoleDao();
        try {
            transactionalDaoFactory.beginTransaction();
            User user = formUser(signUpDto);
            userDao.create(user);

            UserRole userRole = new UserRole();
            Set<Role> roles = user.getAuthorities();
            for (Role r : roles) {
                userRole.setUser(user);
                userRole.setRole(r);
                userRoleDao.create(userRole);
            }

            Client client = formClient(signUpDto, user);
            clientDao.create(client);

            transactionalDaoFactory.commitTransaction();
        } catch (SQLException ex) {
            LOGGER.error("Cannot save user");
            try {
                transactionalDaoFactory.rollbackTransaction();
            } catch (SQLException er) {
                LOGGER.error("Something went wrong with transaction");
            }
        } finally {
            transactionalDaoFactory.close();
        }
    }

    private Client formClient(SignUpDto signUpDto, User user) {
        Client client = new Client();
        client.setFirstName(signUpDto.getFirstName());
        client.setFirstNameUk(signUpDto.getFirstNameUk());
        client.setMiddleName(signUpDto.getMiddleName());
        client.setMiddleNameUk(signUpDto.getMiddleNameUk());
        client.setLastName(signUpDto.getLastName());
        client.setMiddleNameUk(signUpDto.getMiddleNameUk());
        client.setPassport(signUpDto.getPassport());
        client.setBirthday(signUpDto.getBirthday());
        client.setUser(user);
        return client;
    }

    private User formUser(SignUpDto signUpDto) {
        User user = new User();
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.CLIENT);
        user.setAuthorities(roles);
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        return user;
    }

    public Optional<Client> getClientByUser(User user) {
        try (ClientDao clientDao = transactionalDaoFactory.createClientDao()) {
            return clientDao.findByUser(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
