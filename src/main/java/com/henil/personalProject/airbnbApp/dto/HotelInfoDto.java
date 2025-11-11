package com.henil.personalProject.airbnbApp.dto;

import com.henil.personalProject.airbnbApp.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HotelInfoDto {
    private HotelDto hotelDto;
    private List<RoomDto> rooms;
}
