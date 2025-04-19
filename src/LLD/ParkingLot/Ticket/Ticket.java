package LLD.ParkingLot.Ticket;

import LLD.ParkingLot.ParkingSpot.ParkingSpot;
import LLD.ParkingLot.Vehicle.Vehicle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ticket {
    private ParkingSpot parkingSpot;
    private Vehicle vehicle;
    private LocalDateTime startTime;

    public Ticket(ParkingSpot parkingSpot, Vehicle vehicle){
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
        this.startTime = LocalDateTime.now();
    }
}
