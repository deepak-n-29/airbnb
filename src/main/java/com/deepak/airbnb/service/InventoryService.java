package com.deepak.airbnb.service;

import com.deepak.airbnb.entity.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);
}
