package LLD.ParkingLot.Vehicle;

abstract public class Vehicle {
    private String licensePlate;
    private String vehicleTye;

    public Vehicle(String vehicleType, String licencePlate){
        this.vehicleTye = vehicleType;
        this.licensePlate = licencePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVehicleTye() {
        return vehicleTye;
    }

    public abstract double calculateFare(int hoursStayed);
}
