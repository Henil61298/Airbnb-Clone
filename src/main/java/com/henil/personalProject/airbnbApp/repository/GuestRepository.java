package com.henil.personalProject.airbnbApp.repository;

import com.henil.personalProject.airbnbApp.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}