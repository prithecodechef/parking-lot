package com.gojek.demo.parking.lot.comparator;

import com.gojek.demo.parking.lot.model.ParkingSpot;

import java.util.Comparator;

public class ParkingSpotComparator implements Comparator<ParkingSpot> {

    @Override
    public int compare(ParkingSpot o1, ParkingSpot o2) {
        return o1.getSlotNumber() - o2.getSlotNumber();
    }
}
