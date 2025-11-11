package com.henil.personalProject.airbnbApp.repository;

import com.henil.personalProject.airbnbApp.entity.Hotel;
import com.henil.personalProject.airbnbApp.entity.Inventory;
import com.henil.personalProject.airbnbApp.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("select i from Inventory i where i.room = ?1 and i.hotel = ?2 and i.date = ?3")
    Inventory findInventoryByRoomAndHotelAndDate(Room room, Hotel hotel, LocalDate today);

    @Query(""" 
            select distinct i.hotel
            from Inventory i
            where i.city = :city
            and i.date between :startDate and :endDate
            and i.closed = false
            and (i.totalCount - i.bookedCount) >= :roomsCount
            group by i.hotel, i.room
            having count(i.date) = :dateCount""")
    Page<Hotel> findHotelByAvailableInventory(
            String city,
            LocalDate startDate,
            LocalDate endDate,
            Integer roomsCount,
            Long dateCount,
            Pageable pageable
    );
}