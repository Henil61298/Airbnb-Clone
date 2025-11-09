package com.henil.personalProject.airbnbApp.repository;

import com.henil.personalProject.airbnbApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}