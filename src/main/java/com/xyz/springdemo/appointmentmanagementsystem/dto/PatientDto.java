package com.xyz.springdemo.appointmentmanagementsystem.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PatientDto {
    private int id;

    @NotNull(message = "first name Required")
    @Size(min = 3,message = "is required")
    @Pattern(regexp = "[^0-9]*",message = "numbers are not allowed")
    private String firstName;

    @NotNull(message = "last name Required")
    @Size(min = 1,message = "is required")
    @Pattern(regexp = "[^0-9]*",message = "numbers are not allowed")
    private String lastName;

    @NotNull(message = "phone number is required")
    @Pattern(regexp = "[^a-zA-Z]*",message = "letters are not allowed")
    @Size(min = 6,max = 15)
    private String phone;

    @Email
    private String username;

    @NotNull(message = "password is required")
    @Size(min = 4,message = "password length should be greater than 3")
    private String password;
    private int temp;
    public PatientDto() {
    }

    public PatientDto(String firstName, String lastName, String phone, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
