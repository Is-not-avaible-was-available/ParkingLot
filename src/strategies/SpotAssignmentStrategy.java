package strategies;

import Models.ParkingLot;
import Models.ParkingSlot;
import Models.VehicleType;
import Models.Gate;

public interface SpotAssignmentStrategy {
    public ParkingSlot getSpot(VehicleType vehicleType);

}
