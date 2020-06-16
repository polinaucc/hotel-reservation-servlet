package ua.polina.model.entity;

public enum RoomType {
    ECONOMY("room.type.economy"),
    BALCONY("room.type.balcony"),
    BUSINESS("room.type.business"),
    LUXURY("room.type.luxury");

    private final String displayValue;

    RoomType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
