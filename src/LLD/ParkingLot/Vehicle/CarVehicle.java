package LLD.ParkingLot.Vehicle;

public class CarVehicle extends Vehicle{

    private final double RATE = 10.0;

    CarVehicle(String licencePlate, String vehicleTye){
        super(vehicleTye, licencePlate);
    }

    @Override
    public double calculateFare(int hoursStayed) {
        return hoursStayed*RATE;
    }
}
