package ua.polina.model.dto;

import ua.polina.model.entity.RoomType;

import java.math.BigDecimal;

public class DescriptionDto {
    private RoomType roomType;
    private int countOfBeds;
    private int countOfPersons;
    private BigDecimal costPerNight;

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getCountOfBeds() {
        return countOfBeds;
    }

    public void setCountOfBeds(int countOfBeds) {
        this.countOfBeds = countOfBeds;
    }

    public int getCountOfPersons() {
        return countOfPersons;
    }

    public void setCountOfPersons(int countOfPersons) {
        this.countOfPersons = countOfPersons;
    }

    public BigDecimal getCostPerNight() {
        return costPerNight;
    }

    public void setCostPerNight(BigDecimal costPerNight) {
        this.costPerNight = costPerNight;
    }
}
