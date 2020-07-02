package ua.polina.model.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.UserDao;
import ua.polina.model.entity.Role;
import ua.polina.model.entity.User;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    DaoFactory daoFactory;

    @Mock
    UserDao userDao;

    @InjectMocks
    UserService userService;

    User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Field field = UserService.class.getDeclaredField("daoFactory");
        FieldHelper.makeNonFinal(field);
        field.setAccessible(true);
        field.set(userService, daoFactory);

        user = new User();
        user.setId(1L);
        user.setUsername("user1");
        user.setEmail("user1@user.com");
        user.setPassword("user1");
        user.setAuthorities(Set.of(Role.CLIENT));
    }

    @Test
    public void getUserByUsername() {
        when(daoFactory.createUserDao()).thenReturn(userDao);
        Optional<User> expectedUser = Optional.of(user);
        when(userDao.findByUsername("user1")).thenReturn(expectedUser);
        Optional<User> actualUser = userService.getUserByUsername("user1");
        Assert.assertEquals(expectedUser, actualUser);
    }
}