package com.gojek.demo.parking.lot.controller;

import com.gojek.demo.parking.lot.comparator.ParkingSpotComparator;
import com.gojek.demo.parking.lot.constants.Constants;
import com.gojek.demo.parking.lot.model.ParkingLot;
import com.gojek.demo.parking.lot.model.ParkingSpot;
import com.gojek.demo.parking.lot.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ParkingLotController {

    ParkingLot parkingLot;

    public ParkingLotController(int noOfParkingSlots) {
        if (noOfParkingSlots < 0) {
            print(Constants.INVALID_INPUT);
        }
        PriorityQueue<ParkingSpot> emptyParkingSpot = new PriorityQueue<>(noOfParkingSlots, new ParkingSpotComparator());
        for (int i = 0; i < noOfParkingSlots; i++) {
            ParkingSpot parkingSpot = new ParkingSpot(i + 1);
            emptyParkingSpot.add(parkingSpot);
        }
        print(String.format(Constants.PARKING_LOT_CREATED, noOfParkingSlots));
        this.parkingLot = new ParkingLot(noOfParkingSlots, emptyParkingSpot);
    }

    public String park(Vehicle vehicle){
        if(!parkingLot.isParkingSpaceAvailable()){
            print(Constants.NO_SPACE);
            return Constants.NO_SPACE;
        }
        ParkingSpot parkingSpot = parkingLot.parkVehicle(vehicle);
        print(String.format(Constants.ALLOCATED_SLOT, parkingSpot.getSlotNumber()));
        return String.format(Constants.ALLOCATED_SLOT, parkingSpot.getSlotNumber());
    }

    public String removeVehicle(int slotNumber){
        if(!parkingLot.getOccupiedParkingSlots().containsKey(slotNumber)){
            print(Constants.ALREADY_EMPTY);
            return Constants.ALREADY_EMPTY;
        }
        parkingLot.removeVehicleOnSlot(slotNumber);
        print(String.format(Constants.SLOT_FREED, slotNumber));
        return String.format(Constants.SLOT_FREED, slotNumber);
    }

    public void getStatus(){
        if(parkingLot.getOccupiedParkingSlots().isEmpty()){
            print(Constants.ALL_PARKING_SLOT_EMPTY);
            return;
        }
        print(Constants.STATUS_HEADER);
        for(int i = 0; i <= parkingLot.CAPACITY; i++){
            if(parkingLot.getOccupiedParkingSlots().containsKey(i)){
                ParkingSpot parkingSpot = parkingLot.getOccupiedParkingSlots().get(i);
                System.out.format(String.format(Constants.STATUS_BODY, i, parkingSpot.getVehicle().getRegistrationNumber(), parkingSpot.getVehicle().getColor()));
            }
        }
    }

    public String slotNumberForRegistrationNumber(String registrationNumber){
        if(!parkingLot.getParkedVehicles().containsKey(registrationNumber)){
            print(String.format(Constants.NOT_PARKED_IN_PARKING_LOT, registrationNumber));
            return String.format(Constants.NOT_PARKED_IN_PARKING_LOT, registrationNumber);
        }
        Vehicle vehicle = parkingLot.getParkedVehicles().get(registrationNumber);
        print(Integer.toString(vehicle.getParkingSpot().getSlotNumber()));
        return Integer.toString(vehicle.getParkingSpot().getSlotNumber());
    }

    public String slotNumbersForCarsWithColour(String color){
        if(!parkingLot.getColorToVehicleMap().containsKey(color)){
            print(String.format(Constants.COLOR_CAR_NOT_PARKED_IN_PARKING_LOT, color));
            return String.format(Constants.COLOR_CAR_NOT_PARKED_IN_PARKING_LOT, color);
        }
        List<Vehicle> vehicles = parkingLot.getColorToVehicleMap().get(color);
        List<String> slots = new ArrayList<>();
        vehicles.forEach(v -> slots.add(Integer.toString(v.getParkingSpot().getSlotNumber())));
        print(String.join(", ", slots));
        return String.join(", ", slots);
    }

    public String registrationNumbersForCarsWithColour(String color){
        if(!parkingLot.getColorToVehicleMap().containsKey(color)){
            print(String.format(Constants.COLOR_CAR_NOT_PARKED_IN_PARKING_LOT, color));
            return String.format(Constants.COLOR_CAR_NOT_PARKED_IN_PARKING_LOT, color);
        }
        List<Vehicle> vehicles = parkingLot.getColorToVehicleMap().get(color);
        List<String> registrationNos = new ArrayList<>();
        vehicles.forEach(v -> registrationNos.add(v.getRegistrationNumber()));
        print(String.join(", ", registrationNos));
        return String.join(", ", registrationNos);
    }

    private void print(String message){
        System.out.println(message);
    }
}
