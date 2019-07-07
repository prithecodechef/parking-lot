package com.gojek.demo.parking.lot.model;

import com.gojek.demo.parking.lot.comparator.ParkingSpotComparator;
import org.junit.Before;
import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.*;

public class ParkingLotTest {

    private ParkingLot parkingLot;

    @Before
    public void setUp() throws Exception {
        PriorityQueue<ParkingSpot> emptyParkingSlots = new PriorityQueue<>(2, new ParkingSpotComparator());
        emptyParkingSlots.add(new ParkingSpot(1));
        emptyParkingSlots.add(new ParkingSpot(2));
        parkingLot = new ParkingLot(2, emptyParkingSlots);
    }
    @Test
    public void parkVehicle() throws Exception {
        parkingLot.parkVehicle(new Vehicle("123", "red"));
        assertEquals(1, parkingLot.getParkedVehicles().size());
        assertEquals(1, parkingLot.getEmptyParkingSlots().size());
        assertEquals(1, parkingLot.getOccupiedParkingSlots().size());
        parkingLot.parkVehicle(new Vehicle("1234", "blue"));
        assertEquals(2, parkingLot.getParkedVehicles().size());
        assertEquals(0, parkingLot.getEmptyParkingSlots().size());
        assertEquals(2, parkingLot.getOccupiedParkingSlots().size());
        parkingLot.parkVehicle(new Vehicle("12345", "white"));
        assertEquals(2, parkingLot.getParkedVehicles().size());
        assertEquals(0, parkingLot.getEmptyParkingSlots().size());
        assertEquals(2, parkingLot.getOccupiedParkingSlots().size());
    }

    @Test
    public void removeVehicleOnSlot() throws Exception {
        parkingLot.parkVehicle(new Vehicle("123", "red"));
        parkingLot.parkVehicle(new Vehicle("1234", "blue"));
        parkingLot.parkVehicle(new Vehicle("12345", "white"));
        parkingLot.removeVehicleOnSlot(1);
        assertEquals(1, parkingLot.getParkedVehicles().size());
        assertEquals(1, parkingLot.getEmptyParkingSlots().size());
        assertEquals(1, parkingLot.getOccupiedParkingSlots().size());
        parkingLot.removeVehicleOnSlot(2);
        assertEquals(0, parkingLot.getParkedVehicles().size());
        assertEquals(2, parkingLot.getEmptyParkingSlots().size());
        assertEquals(0, parkingLot.getOccupiedParkingSlots().size());
    }

}