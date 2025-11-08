package com.henil.personalProject.airbnbApp.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

@Embeddable
public class HotelContactInfo {
    private String address;
    private String phoneNumber;
    private String email;
    private String location;
}
