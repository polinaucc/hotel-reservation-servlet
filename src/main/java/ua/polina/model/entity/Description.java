package ua.polina.model.entity;

import java.math.BigDecimal;

public class Description {
    private Long id;
    private RoomType roomType;
    private int countOfPersons;
    private int countOfBeds;
    private BigDecimal costPerNight;

    @Override
    public String toString() {
        return roomType + " for " + countOfPersons + " persons " + "with " +
                countOfBeds +" beds" + "\n cost per night is " + costPerNight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getCountOfPersons() {
        return countOfPersons;
    }

    public void setCountOfPersons(int countOfPersons) {
        this.countOfPersons = countOfPersons;
    }

    public int getCountOfBeds() {
        return countOfBeds;
    }

    public void setCountOfBeds(int countOfBeds) {
        this.countOfBeds = countOfBeds;
    }

    public BigDecimal getCostPerNight() {
        return costPerNight;
    }

    public void setCostPerNight(BigDecimal costPerNight) {
        this.costPerNight = costPerNight;
    }
}
