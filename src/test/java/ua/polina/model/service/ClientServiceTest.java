package ua.polina.model.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.junit.MockitoJUnitRunner;
import ua.polina.model.dao.ClientDao;
import ua.polina.model.dao.UserDao;
import ua.polina.model.dao.UserRoleDao;
import ua.polina.model.dao.implementation.TransactionalDaoFactory;
import ua.polina.model.dto.SignUpDto;
import ua.polina.model.entity.Client;
import ua.polina.model.entity.Role;
import ua.polina.model.entity.User;
import ua.polina.model.entity.UserRole;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {
    Client client;
    @Mock
    TransactionalDaoFactory transactionalDaoFactory;
    SignUpDto signUpDto;
    User user;
    UserRole userRole;
    @Mock
    ClientDao clientDao;
    @Mock
    UserDao userDao;
    @Mock
    UserRoleDao userRoleDao;
    @InjectMocks
    ClientService clientService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        client = new Client();
        client.setId(1L);
        client.setFirstNameUk("Антон");
        client.setFirstName("Anton");
        client.setMiddleName("Volodymyrovych");
        client.setMiddleNameUk("Володимирович");
        client.setLastName("Povarov");
        client.setLastNameUk("Поваров");
        client.setPassport("АБ456894");
        client.setBirthday(LocalDate.of(1999, 4, 18));
        client.setUser(new User());

        signUpDto = new SignUpDto();
        signUpDto.setEmail("testUser@ukr.net");
        signUpDto.setUsername("testUser");
        signUpDto.setPassword("testuser");
        signUpDto.setFirstNameUk("Антон");
        signUpDto.setFirstName("Anton");
        signUpDto.setMiddleName("Volodymyrovych");
        signUpDto.setMiddleNameUk("Володимирович");
        signUpDto.setLastName("Povarov");
        signUpDto.setLastNameUk("Поваров");
        signUpDto.setPassport("АБ456894");
        signUpDto.setBirthday(LocalDate.of(1999, 4, 18));

        user = new User();
        user.setId(1L);
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.CLIENT);
        user.setAuthorities(roles);
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());

        userRole = new UserRole();
        Set<Role> userAuthorities = user.getAuthorities();
        for (Role r : userAuthorities) {
            userRole.setUser(user);
            userRole.setRole(r);
        }
    }

    @Test
    public void saveNewClient() throws Exception {
        Whitebox.setInternalState(clientService, "transactionalDaoFactory", transactionalDaoFactory);

        when(transactionalDaoFactory.createClientDao()).thenReturn(clientDao);
        when(transactionalDaoFactory.createUserDao()).thenReturn(userDao);
        when(transactionalDaoFactory.createUserRoleDao()).thenReturn(userRoleDao);

        clientService.saveNewClient(signUpDto);

        Mockito.verify(userDao, Mockito.times(1)).create(Mockito.any());
        Mockito.verify(clientDao, Mockito.times(1)).create(Mockito.any());
        Mockito.verify(userRoleDao, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    public void getClientByUser() throws Exception {
        Whitebox.setInternalState(clientService, "transactionalDaoFactory", transactionalDaoFactory);
        given(transactionalDaoFactory.createClientDao()).willReturn(clientDao);
        Optional<Client> expectedClient = Optional.of(client);
        given(clientDao.findByUser(1L))
                .willReturn(expectedClient);
        Optional<Client> actualClient = clientService.getClientByUser(user);
        Mockito.verify(clientDao, Mockito.times(1)).findByUser(Mockito.any());
        Assert.assertEquals(expectedClient, actualClient);
    }
}