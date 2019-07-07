package com.gojek.demo.parking.lot.app;

import com.gojek.demo.parking.lot.constants.Commands;
import com.gojek.demo.parking.lot.controller.ParkingLotController;
import com.gojek.demo.parking.lot.model.Vehicle;

import java.io.*;

public class ParkingLotApplication {

    ParkingLotController parkingLotController;


    public static void main(String[] args) {
        ParkingLotApplication parkingLotApplication = new ParkingLotApplication();
        if (args == null || args.length == 0) {
            parkingLotApplication.takeInputFromCommandPrompt();
        } else {
            parkingLotApplication.takeInputFromFile(args[0]);
        }

    }

    private void takeInputFromFile(String filePath) {
        File file = new File(filePath);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("Error Reading file");
        }

        String command;
        try {
            while ((command = br.readLine()) != null)
                if (command != null) {
                    executeCommand(command.split(" "));
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void takeInputFromCommandPrompt() {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String command = null;
            try {
                command = reader.readLine();
            } catch (IOException e) {
                System.out.print("something went wrong");
            }
            if (command != null) {
                executeCommand(command.split(" "));
            }
        }
    }

    private void executeCommand(String[] command) {
        switch (command[0]) {
            case Commands.CREATE_PARKING_LOT:
                if (command.length >= 2) {
                    parkingLotController = new ParkingLotController(Integer.valueOf(command[1]));
                    return;
                }
                break;
            case Commands.PARK:
                if (command.length >= 3) {
                    if (parkingLotController != null) {
                        parkingLotController.park(new Vehicle(command[1], command[2]));//write the assumption
                        return;
                    }
                }
                break;
            case Commands.LEAVE:
                if (command.length >= 2) {
                    if (parkingLotController != null) {
                        parkingLotController.removeVehicle(Integer.valueOf(command[1]));
                        return;
                    }
                }
                break;
            case Commands.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                if (command.length >= 2) {
                    if (parkingLotController != null) {
                        parkingLotController.registrationNumbersForCarsWithColour(command[1]);
                        return;
                    }
                }
                break;
            case Commands.SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                if (command.length >= 2) {
                    if (parkingLotController != null) {
                        parkingLotController.slotNumberForRegistrationNumber(command[1]);
                        return;
                    }
                }
                break;
            case Commands.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                if (command.length >= 2) {
                    if (parkingLotController != null) {
                        parkingLotController.slotNumbersForCarsWithColour(command[1]);
                        return;
                    }
                }
                break;
            case Commands.STATUS:
                if (parkingLotController != null) {
                    parkingLotController.getStatus();
                    return;
                }
                break;
            case Commands.EXIT:
                System.exit(0);
        }
        System.out.println("Something Went Wrong");
    }
}
