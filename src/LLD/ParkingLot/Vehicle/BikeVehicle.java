package LLD.ParkingLot.Vehicle;

public class BikeVehicle extends  Vehicle{
    private final double RATE = 5.0;

    BikeVehicle(String licencePlate, String vehicleTye){
        super(vehicleTye, licencePlate);
    }

    @Override
    public double calculateFare(int hoursStayed) {
        return hoursStayed*RATE;
    }
}
