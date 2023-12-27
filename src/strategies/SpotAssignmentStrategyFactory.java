package strategies;

import Models.SlotAllotmentStrategyType;

public class SpotAssignmentStrategyFactory {
    public static SpotAssignmentStrategy getSpotForType(SlotAllotmentStrategyType slotAllotmentStrategyType){

        return new RandomSpotAssignmentStrategy();
    }
}
