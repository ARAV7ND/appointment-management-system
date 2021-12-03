package com.xyz.springdemo.appointmentmanagementsystem.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DoctorDto {
    private int id;

    @NotNull
    @Size(min = 4,message = "is required")
    @Pattern(regexp = "[^0-9]*",message = "numbers are not allowed")
    private String firstName;

    @NotNull
    @Size(min = 1,message = "is required")
    @Pattern(regexp = "[^0-9]*",message = "numbers are not allowed")
    private String lastName;

    @Email
    private String username;

    @NotNull(message = "password is required")
    @Size(min = 4,message = "password length should be greater than 3")
    private String password;

    @NotNull(message = "address is required")
    @Size(min = 5,max = 25)
    private String address;

    @NotNull(message = "address is required")
    @Size(min = 5,max=25)
    @Pattern(regexp = "[^0-9]*",message = "numbers are not allowed")
    private String specialist;


    private int temp;

    public DoctorDto() {
    }

    public DoctorDto(String firstName, String lastName, String username, String password, String address, String specialist) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.address = address;
        this.specialist = specialist;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
