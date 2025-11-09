package com.henil.personalProject.airbnbApp.service;

import com.henil.personalProject.airbnbApp.entity.Hotel;
import com.henil.personalProject.airbnbApp.entity.Inventory;
import com.henil.personalProject.airbnbApp.entity.Room;
import com.henil.personalProject.airbnbApp.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    private Boolean checkIfInventoryAlreadyExist(Room room, Hotel hotel){
        LocalDate today = LocalDate.now();
        Inventory inventory = inventoryRepository.findInventoryByRoomAndHotelAndDate(room, hotel, today);
        return inventory != null;
    }

    public void initializeInventory(Room room){
        Hotel hotel = room.getHotel();
        if (!checkIfInventoryAlreadyExist(room, hotel) && hotel.getActive()){
            LocalDate oneYearLater = LocalDate.now().plusYears(1);
            for (LocalDate i = LocalDate.now();i.isBefore(oneYearLater);i = i.plusDays(1)){
                Inventory inventory = Inventory
                        .builder()
                        .hotel(hotel)
                        .room(room)
                        .bookedCount(0)
                        .city(room.getHotel().getCity())
                        .date(i)
                        .price(room.getPrice())
                        .surgeFactor(BigDecimal.ONE)
                        .totalCount(room.getTotalCount())
                        .closed(false)
                        .build();
                inventoryRepository.save(inventory);
            }
        }
    }
}
