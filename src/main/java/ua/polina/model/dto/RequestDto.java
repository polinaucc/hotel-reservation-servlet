package ua.polina.model.dto;

import ua.polina.model.entity.RoomType;

import java.time.LocalDate;

public class RequestDto {
    private RoomType roomType;
    private int countOfPerson;
    private int countOfBeds;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getCountOfPerson() {
        return countOfPerson;
    }

    public void setCountOfPerson(int countOfPerson) {
        this.countOfPerson = countOfPerson;
    }

    public int getCountOfBeds() {
        return countOfBeds;
    }

    public void setCountOfBeds(int countOfBeds) {
        this.countOfBeds = countOfBeds;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
