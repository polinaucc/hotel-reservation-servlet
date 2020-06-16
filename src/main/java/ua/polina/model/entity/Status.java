package ua.polina.model.entity;

public enum Status {
    New_request("new.request"),
    Accepted("accepted"),
    Rejected("rejected");

    private String message;

    Status(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
