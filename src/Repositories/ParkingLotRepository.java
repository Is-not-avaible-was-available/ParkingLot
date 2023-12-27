package Repositories;

import Models.Gate;
import Models.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {
    private Map<Long, ParkingLot> parkingLots = new HashMap<>();
    public ParkingLot getParkingLotFromGate(Gate gate){

        for(ParkingLot parkingLot : parkingLots.values()){
            if(parkingLot.getGates().contains(gate)){
                return parkingLot;
            }
        }

        return null;
    }
}
