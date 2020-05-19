package ua.polina.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return countOfPersons == that.countOfPersons &&
                countOfBeds == that.countOfBeds &&
                roomType == that.roomType &&
                costPerNight.compareTo(that.costPerNight)==0;

    }

    @Override
    public int hashCode() {
        return Objects.hash(roomType, countOfPersons, countOfBeds, costPerNight);
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
