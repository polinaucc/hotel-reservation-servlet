package ua.polina.model.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.RoomDao;
import ua.polina.model.dto.RoomDto;
import ua.polina.model.entity.Description;
import ua.polina.model.entity.Room;
import ua.polina.model.entity.RoomType;
import ua.polina.model.exception.DataExistsException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceTest {
    @Mock
    DaoFactory daoFactory;
    @Mock
    RoomDao roomDao;
    RoomDto roomDto;
    Description description;
    @InjectMocks
    RoomService roomService;
    Room room;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Field field = RoomService.class.getDeclaredField("daoFactory");
        FieldHelper.makeNonFinal(field);
        field.setAccessible(true);
        field.set(roomService, daoFactory);

        roomDto = new RoomDto();
        roomDto.setDescriptionId(1L);
        roomDto.setRoomNumber("11r");

        description = new Description();
        description.setId(1L);
        description.setRoomType(RoomType.BUSINESS);
        description.setCountOfPersons(2);
        description.setCountOfBeds(1);
        description.setCostPerNight(new BigDecimal(1500));

        room = new Room();
        room.setId(1L);
        room.setDescription(description);
        room.setRoomNumber("11r");
    }

    @Test
    public void saveRoom() throws DataExistsException {
        when(daoFactory.createRoomDao()).thenReturn(roomDao);
        roomService.saveRoom(roomDto, description);
        verify(roomDao, times(1)).create(any(Room.class));
    }

    @Test
    public void getRoomsByDescription() {
        when(daoFactory.createRoomDao()).thenReturn(roomDao);
        List<Room> expectedList = Collections.singletonList(room);
        when(roomDao.findByDescription(description.getId())).thenReturn(expectedList);
        List<Room> actualList = roomService.getRoomsByDescription(description);
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void getRoomById() {
        when(daoFactory.createRoomDao()).thenReturn(roomDao);
        Optional<Room> expectedRoom = Optional.of(room);
        when(roomDao.findById(1L)).thenReturn(room);
        Optional<Room> actualRoom = roomService.getRoomById(1L);
        Assert.assertEquals(expectedRoom, actualRoom);
    }
}