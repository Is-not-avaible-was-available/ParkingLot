package services;

import Exceptions.GateNotFoundException;
import Models.*;
import Repositories.GateRepository;
import Repositories.ParkingLotRepository;
import Repositories.TicketRepository;
import Repositories.VehicleRepository;
import strategies.SpotAssignmentStrategy;
import strategies.SpotAssignmentStrategyFactory;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         ParkingLotRepository parkingLotRepository,
                         TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }


    public Ticket issueTicket(
            VehicleType vehicleType
            , String vehicleNumber
            , String vehicleOwnerName
            , Long gateId
    ) throws GateNotFoundException {
        /*
        1.create a ticket
        2.find  the spot
        3.save to database
        4.return ticket
        */

        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        Optional<Gate>gateOptional = gateRepository.findByGateId(gateId);
        if(gateOptional.isEmpty()){
            throw new GateNotFoundException("Gate not found");
        }
        Gate gate = gateOptional.get();
        ticket.setGate(gate);
        ticket.setGeneratedBy(gate.getOperator());

        //check if vehicle in Db
        //1. Yes
        //get the vehicle from the db
        //put it on the ticket object
        //2. No
        //create new vehicle
        //save it to the db
        //put it in the ticket object

        Optional<Vehicle> optionalVehicle = vehicleRepository.
                findVehicleByVehicleNumber(vehicleNumber);

        Vehicle savedVehicle;

        if(optionalVehicle.isEmpty()){
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleType(vehicleType);
            vehicle.setNumber(vehicleNumber);
            vehicle.setOwnerName(vehicleOwnerName);

            savedVehicle = vehicleRepository.saveVehicle(vehicle);
        }else{
            savedVehicle = optionalVehicle.get();
        }
        ticket.setVehicle(savedVehicle);

        SlotAllotmentStrategyType slotAllotmentStrategyType = parkingLotRepository.
                getParkingLotFromGate(gate).getSlotAllotmentStrategy();

        SpotAssignmentStrategy spotAssignmentStrategy = SpotAssignmentStrategyFactory
                .getSpotForType(slotAllotmentStrategyType);

        ticket.setParkingSlot(spotAssignmentStrategy.getSpot(vehicleType));


        Ticket savedTicket = ticketRepository.saveTicket(ticket);
        ticket.setNumber("Ticket: " + savedTicket.getId());


        return savedTicket;
    }
}
