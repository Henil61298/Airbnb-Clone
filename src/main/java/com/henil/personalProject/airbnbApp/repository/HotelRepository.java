package com.henil.personalProject.airbnbApp.repository;

import com.henil.personalProject.airbnbApp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}