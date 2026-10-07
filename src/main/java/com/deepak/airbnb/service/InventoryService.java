package com.deepak.airbnb.service;

import com.deepak.airbnb.dto.HotelDto;
import com.deepak.airbnb.dto.HotelPriceDto;
import com.deepak.airbnb.dto.HotelSearchRequest;
import com.deepak.airbnb.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {
    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
