package LLD.ParkingLot.ParkingSpot;

import LLD.ParkingLot.Vehicle.Vehicle;

public abstract class ParkingSpot {

    private int spotNumber;
    private String spotType;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSpot(int spotNumber, String spotType){
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isOccupied = false;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public abstract boolean canParkVehicle(Vehicle vehicle);

    public void parkVehicle(Vehicle vehicle){
        if (!canParkVehicle(vehicle)){
            throw new IllegalStateException("can't park this vehicle at this spot");
        }
        if (isOccupied) {
            throw new IllegalStateException("Lot is already occupied!");
        }
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle(Vehicle vehicle){
        if (!isOccupied){
            throw new IllegalStateException("Lot is already empty!");
        }
        this.vehicle = null;
        this.isOccupied = true;
    }

    public int getSpotNumber(){
        return this.spotNumber;
    }

    public String getSpotType(){
        return this.spotType;
    }

    public Vehicle getVehicle(){
        return this.vehicle;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotNumber=" + spotNumber +
                ", isOccupied=" + isOccupied +
                ", vehicle=" + (vehicle != null ? vehicle.getLicensePlate() : "None") +
                '}';
    }


}
