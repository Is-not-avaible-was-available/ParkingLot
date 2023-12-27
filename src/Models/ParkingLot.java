package Models;


import java.util.List;

public class ParkingLot extends BaseModel{
    private List<ParkingFloor> floors;
    private List<Gate> gates;
    private FeeCalculationStrategyType feeCalculationStrategyType;
    private SlotAllotmentStrategyType slotAllotmentStrategyType;
    private String  address;
    private ParkingLotStatus parkingLotStatus;


    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }

    public FeeCalculationStrategyType getFeeCalculationStrategy() {
        return feeCalculationStrategyType;
    }

    public void setFeeCalculationStrategyType(FeeCalculationStrategyType feeCalculationStrategyType) {
        this.feeCalculationStrategyType = feeCalculationStrategyType;
    }

    public SlotAllotmentStrategyType getSlotAllotmentStrategy() {
        return slotAllotmentStrategyType;
    }

    public void setSlotAllotmentStrategyType(SlotAllotmentStrategyType slotAllotmentStrategyType) {
        this.slotAllotmentStrategyType = slotAllotmentStrategyType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
