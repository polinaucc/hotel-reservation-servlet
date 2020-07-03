package ua.polina.model.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.RequestDao;
import ua.polina.model.dto.RequestDto;
import ua.polina.model.entity.*;
import ua.polina.model.exception.DataExistsException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RequestServiceTest {
    Request request;
    @Mock
    RequestDto requestDto;
    Client client;
    Description description;
    @Mock
    RequestDao requestDao;
    @Mock
    DaoFactory daoFactory;
    @InjectMocks
    RequestService requestService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Field field = RequestService.class.getDeclaredField("daoFactory");
        FieldHelper.makeNonFinal(field);
        field.setAccessible(true);
        field.set(requestService, daoFactory);

        description = new Description();
        description.setId(1L);
        description.setRoomType(RoomType.BUSINESS);
        description.setCountOfPersons(2);
        description.setCountOfBeds(1);
        description.setCostPerNight(new BigDecimal(1500));

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

        request = new Request();
        request.setId(1L);
        request.setDescription(description);
        request.setClient(client);
        request.setCheckInDate(LocalDate.of(2020, 7, 5));
        request.setCheckOutDate(LocalDate.of(2020, 7, 9));
        request.setStatus(Status.New_request);
    }

    @Test
    public void saveNewRequest() throws DataExistsException {
        when(daoFactory.createRequestDao()).thenReturn(requestDao);
        requestService.saveNewRequest(requestDto, client, description);
        verify(requestDao, times(1)).create(Mockito.any(Request.class));
    }

    @Test
    public void getAllRequests() {
        List<Request> expectedList = Collections.singletonList(request);
        when(daoFactory.createRequestDao()).thenReturn(requestDao);
        when(requestDao.findAll()).thenReturn(expectedList);
        List<Request> actualList = requestService.getAllRequests();
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void getAllRequestsPagination() {
        List<Request> expectedList = Collections.singletonList(request);
        when(daoFactory.createRequestDao()).thenReturn(requestDao);
        when(requestDao.findAll(1, 1)).thenReturn(expectedList);
        List<Request> actualList = requestService.getAllRequestsPagination(1, 1);
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void getRequestById() {
        Request expectedRequest = request;
        when(daoFactory.createRequestDao()).thenReturn(requestDao);
        when(requestDao.findById(1L)).thenReturn(expectedRequest);
        Request actualRequest = requestService.getRequestById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Error"));
        Assert.assertEquals(expectedRequest, actualRequest);
    }

    @Test
    public void update() {
        when(daoFactory.createRequestDao()).thenReturn(requestDao);
        requestService.update(request, Status.Accepted);
        verify(requestDao, times(1)).update(request);
    }

    @Test
    public void getRequestsByClient() {
        List<Request> expectedList = Collections.singletonList(request);
        when(daoFactory.createRequestDao()).thenReturn(requestDao);
        when(requestDao.findByClient(client.getId())).thenReturn(expectedList);
        List<Request> actualList = requestService.getRequestsByClient(client);
        Assert.assertEquals(expectedList, actualList);
    }
}