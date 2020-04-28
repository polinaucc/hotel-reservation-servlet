package ua.polina.model.entity;

public enum RoomType {
    ECONOMY("Economy"),
    BALCONY("Balcony"),
    BUSINESS("Business"),
    LUXURY("Luxury");

    private final String displayValue;

    RoomType(String displayValue) {
        this.displayValue = displayValue;
    }
}
