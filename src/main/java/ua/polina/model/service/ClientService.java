package ua.polina.model.service;

import ua.polina.model.dao.ClientDao;
import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.UserDao;
import ua.polina.model.dao.UserRoleDao;
import ua.polina.model.dto.SignUpDto;
import ua.polina.model.entity.Client;
import ua.polina.model.entity.Role;
import ua.polina.model.entity.User;
import ua.polina.model.entity.UserRole;
import ua.polina.model.exception.DataExistsException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ClientService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public ClientService() {
    }

    public void saveNewClient(SignUpDto signUpDto) throws Exception {
        try (ClientDao clientDao = daoFactory.createClientDao()) {
            User user = saveUser(signUpDto);
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
           clientDao.create(client);
        }
    }

    private User saveUser(SignUpDto signUpDto) throws Exception {
        try (UserDao userDao = daoFactory.createUserDao()) {
            User user = new User();
            HashSet<Role> roles = new HashSet<>();
            roles.add(Role.CLIENT);
            user.setAuthorities(roles);
            user.setUsername(signUpDto.getUsername());
            user.setEmail(signUpDto.getEmail());
            user.setPassword(signUpDto.getPassword());
            userDao.create(user);
            saveRoles(user);
            return user;
        }

    }

    private void saveRoles(User user) {
        try (UserRoleDao userRoleDao = daoFactory.createUserRoleDao()) {
            UserRole userRole = new UserRole();
            Set<Role> roles = user.getAuthorities();
            for (Role r : roles) {
                userRole.setUser(user);
                userRole.setRole(r);
                userRoleDao.create(userRole);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Client> getClientByUser(User user) {
        try (ClientDao clientDao = daoFactory.createClientDao()) {
            return clientDao.findByUser(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
