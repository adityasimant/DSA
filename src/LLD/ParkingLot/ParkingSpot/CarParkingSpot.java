package LLD.ParkingLot.ParkingSpot;

import LLD.ParkingLot.Vehicle.Vehicle;

public class CarParkingSpot extends ParkingSpot{

    CarParkingSpot(int spotNumber, String spotType){
        super(spotNumber, spotType);
    }

    @Override
    public boolean canParkVehicle(Vehicle vehicle){
        String type = vehicle.getVehicleTye().toLowerCase();
        return type.equals("car");
    }
}
