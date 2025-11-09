package com.henil.personalProject.airbnbApp.service;

import com.henil.personalProject.airbnbApp.Advice.ApiResponse;
import com.henil.personalProject.airbnbApp.Exceptions.ResourceNotFoundException;
import com.henil.personalProject.airbnbApp.dto.HotelDto;
import com.henil.personalProject.airbnbApp.entity.Hotel;
import com.henil.personalProject.airbnbApp.repository.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper mapper;

    public HotelDto createNewHotel(HotelDto hotelDto){
        log.info("Creating hotel with name: " + hotelDto.getName());
        Hotel hotel = mapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        log.info("Created new hotel with name: " + hotel.getName());
        hotelRepository.save(hotel);
        return hotelDto;
    }

    public Hotel hotelExistById(Long id){
        return hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
    }

    public HotelDto findHotelById( Long id){
        log.info("Finding hotel by id: {}", id);
        Hotel hotel = hotelExistById(id);
        return mapper.map(hotel, HotelDto.class);
    }

    public HotelDto updateHotel(Long id, HotelDto hotelDto){
        log.info("Updating details of hotel with id: {}", id);
        Hotel hotel = hotelExistById(id);
        mapper.map(hotelDto, hotel);
        hotelRepository.save(hotel);
        log.info("Hotel details updated with id: {}", id);
        return hotelDto;
    }

    public ApiResponse<String> deleteHotelById(Long id){
        log.info("Deleting hotel with id: {}", id);
        Hotel hotel = hotelExistById(id);
        hotelRepository.delete(hotel);
        log.info("Hotel with id: {} is deleted", id);
        return new ApiResponse<>("Deleted");
    }

    public ApiResponse<String> activateHotelById(Long id){
        log.info("Activating hotel with id: {}", id);
        Hotel hotel = hotelExistById(id);
        hotel.setActive(true);
        hotelRepository.save(hotel);
        log.info("hotel with id: {} is activated successfully", id);
        return new ApiResponse<>("success");
    }
}
