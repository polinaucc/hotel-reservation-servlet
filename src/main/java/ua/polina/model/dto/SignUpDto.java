package ua.polina.model.dto;

import java.time.LocalDate;

public class SignUpDto {
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String firstNameUk;
    private String middleName;
    private String middleNameUk;
    private String lastName;
    private String lastNameUk;
    private String passport;
    private LocalDate birthday;

    public SignUpDto(String email, String username, String password, String firstName,
                     String middleName, String lastName, String passport, LocalDate birthday) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.passport = passport;
        this.birthday = birthday;
    }

    public SignUpDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getFirstNameUk() {
        return firstNameUk;
    }

    public void setFirstNameUk(String firstNameUk) {
        this.firstNameUk = firstNameUk;
    }

    public String getMiddleNameUk() {
        return middleNameUk;
    }

    public void setMiddleNameUk(String middleNameUk) {
        this.middleNameUk = middleNameUk;
    }

    public String getLastNameUk() {
        return lastNameUk;
    }

    public void setLastNameUk(String lastNameUk) {
        this.lastNameUk = lastNameUk;
    }
}
