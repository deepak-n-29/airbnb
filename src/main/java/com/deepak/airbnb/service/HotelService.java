package com.deepak.airbnb.service;

import com.deepak.airbnb.dto.HotelDto;
import com.deepak.airbnb.dto.HotelInfoDto;
import com.deepak.airbnb.entity.Hotel;

import java.util.List;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(Long id);
    void activateHotelById(Long id);


    HotelInfoDto getHotelInfoById(Long hotelId);
}
