package com.henil.personalProject.airbnbApp.dto;

import com.henil.personalProject.airbnbApp.entity.*;
import com.henil.personalProject.airbnbApp.entity.enums.BookingStatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Integer roomsCount;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Payment payment;

    private BookingStatus bookingStatus;

    private Set<Guest> guests;

    private BigDecimal amount;

}
