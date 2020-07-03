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
import ua.polina.model.dao.DescriptionDao;
import ua.polina.model.dto.DescriptionDto;
import ua.polina.model.dto.RequestDto;
import ua.polina.model.entity.Description;
import ua.polina.model.entity.RoomType;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DescriptionServiceTest {
    @Mock
    DaoFactory daoFactory;
    RequestDto requestDto;
    Description description;
    @Mock
    DescriptionDao descriptionDao;
    DescriptionDto descriptionDto;
    @InjectMocks
    DescriptionService descriptionService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Field field = DescriptionService.class.getDeclaredField("daoFactory");
        FieldHelper.makeNonFinal(field);
        field.setAccessible(true);
        field.set(descriptionService, daoFactory);

        descriptionDto = new DescriptionDto();
        descriptionDto.setRoomType(RoomType.BUSINESS);
        descriptionDto.setCountOfPersons(2);
        descriptionDto.setCountOfBeds(1);
        descriptionDto.setCostPerNight(new BigDecimal(1500));

        requestDto = new RequestDto();
        requestDto.setRoomType(RoomType.BUSINESS);
        requestDto.setCountOfBeds(1);
        requestDto.setCountOfPerson(2);
        requestDto.setCheckInDate(LocalDate.of(2020, 7, 5));
        requestDto.setCheckOutDate(LocalDate.of(2020, 7, 9));

        description = new Description();
        description.setId(1L);
        description.setRoomType(RoomType.BUSINESS);
        description.setCountOfPersons(2);
        description.setCountOfBeds(1);
        description.setCostPerNight(new BigDecimal(1500));

    }

    @Test
    public void saveNewDescription() throws Exception {
        Mockito.when(daoFactory.createDescriptionDao()).thenReturn(descriptionDao);
        descriptionService.saveNewDescription(descriptionDto);
        verify(descriptionDao, times(1)).create(Mockito.any());
    }

    @Test
    public void getDescriptionByParameters() throws Exception {
        Mockito.when(daoFactory.createDescriptionDao()).thenReturn(descriptionDao);

        Mockito.when(descriptionDao.findDescriptionByRoomTypeAndCountOfPersonsAndCountOfBeds(
                requestDto.getRoomType(), requestDto.getCountOfPerson(), requestDto.getCountOfBeds()))
                .thenReturn(Optional.of(description));

        Optional<Description> actualDescription = descriptionService.getDescriptionByParameters(requestDto);

        Assert.assertEquals(Optional.of(description), actualDescription);
    }

    @Test
    public void getAllDescriptions() {
        Mockito.when(daoFactory.createDescriptionDao()).thenReturn(descriptionDao);
        List<Description> expected = Collections.singletonList(description);
        given(descriptionDao.findAll()).willReturn(expected);
        List<Description> actualList = descriptionService.getAllDescriptions();
        Assert.assertEquals(expected, actualList);

    }

    @Test
    public void getDescriptionById() throws Exception {
        Mockito.when(daoFactory.createDescriptionDao()).thenReturn(descriptionDao);
        given(descriptionDao.findById(1L)).willReturn(description);
        Optional<Description> actualDescription = descriptionService.getDescriptionById(1L);
        Assert.assertEquals(Optional.of(description), actualDescription);
    }
}