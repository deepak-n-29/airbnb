package com.deepak.airbnb.service;

import com.deepak.airbnb.dto.BookingDto;
import com.deepak.airbnb.dto.BookingRequest;
import com.deepak.airbnb.dto.GuestDto;

import java.util.List;

public interface BookingService {


    BookingDto initializeBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
