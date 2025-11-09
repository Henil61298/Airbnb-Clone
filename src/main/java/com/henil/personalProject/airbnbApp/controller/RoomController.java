package com.henil.personalProject.airbnbApp.controller;

import com.henil.personalProject.airbnbApp.Advice.ApiResponse;
import com.henil.personalProject.airbnbApp.dto.RoomDto;
import com.henil.personalProject.airbnbApp.service.RoomService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/{hotelId}")
    public RoomDto createNewRoom(@PathVariable Long hotelId, @RequestBody RoomDto roomDto){
        return roomService.createNewRoom(hotelId, roomDto);
    }

    @GetMapping("/getAllRoomsFromHotel/{hotelId}")
    public List<RoomDto> getAllRoomsFromHotel(@PathVariable Long hotelId){
        return roomService.getAllRoomsFromHotel(hotelId);
    }

    @GetMapping("/{roomId}")
    public RoomDto findRoomById(@PathVariable Long roomId){
        return roomService.findRoomById(roomId);
    }

    @DeleteMapping("/{roomId}")
    public ApiResponse<String> deleteRoomById(@PathVariable Long roomId){
        return roomService.deleteRoomById(roomId);
    }
}
