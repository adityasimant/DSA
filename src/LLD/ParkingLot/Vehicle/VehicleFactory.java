package LLD.ParkingLot.Vehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(String licencePlate, String vehicleType){
        if (vehicleType.toLowerCase().equals("car")){
            return new CarVehicle(licencePlate, vehicleType);
        } else if (vehicleType.toLowerCase().equals("bike")) {
            return new BikeVehicle(licencePlate, vehicleType);
        }
        return null;
    }
}
