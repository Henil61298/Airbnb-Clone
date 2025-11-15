package com.henil.personalProject.airbnbApp.service;

import com.henil.personalProject.airbnbApp.dto.HotelDto;
import com.henil.personalProject.airbnbApp.dto.HotelInfoDto;
import com.henil.personalProject.airbnbApp.dto.HotelSearchRequest;
import com.henil.personalProject.airbnbApp.dto.RoomDto;
import com.henil.personalProject.airbnbApp.entity.Hotel;
import com.henil.personalProject.airbnbApp.entity.Inventory;
import com.henil.personalProject.airbnbApp.entity.Room;
import com.henil.personalProject.airbnbApp.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ModelMapper mapper;

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

    public Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest) {
        Pageable pageable = PageRequest.of(hotelSearchRequest.getPage(), hotelSearchRequest.getSize());
        Long dateCount =
                ChronoUnit.DAYS.between(hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate()) + 1;

        Page<Hotel> hotelPage = inventoryRepository.findHotelByAvailableInventory(
                hotelSearchRequest.getCity(),
                hotelSearchRequest.getStartDate(),
                hotelSearchRequest.getEndDate(),
                hotelSearchRequest.getRoomsCount(),
                dateCount,
                pageable
        );

        return hotelPage.map((e) -> mapper.map(e, HotelDto.class));
    }

    public HotelInfoDto getHotelInfoById(Hotel hotel) {
        log.info("Fetching hotel info for hotel with id: {}", hotel.getId());
        HotelDto hotelDto = mapper.map(hotel, HotelDto.class);

        List<Room> roomList = hotel.getRooms();

        List<RoomDto> roomDtoList = roomList
                .stream()
                .map((element) -> mapper.map(element, RoomDto.class))
                .toList();

        return new HotelInfoDto(hotelDto, roomDtoList);
    }
}
