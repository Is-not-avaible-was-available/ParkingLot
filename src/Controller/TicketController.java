package Controller;

import DTOs.IssueTicketRequestDto;
import DTOs.IssueTicketResponseDto;
import DTOs.ResponseStatus;
import Models.Ticket;
import services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }
    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto issueTicketRequestDto){
        IssueTicketResponseDto response = new IssueTicketResponseDto();
        Ticket ticket;

        try {
             ticket = ticketService.issueTicket(issueTicketRequestDto.getVehicleType()
                    ,issueTicketRequestDto.getVehicleNumber()
                    ,issueTicketRequestDto.getVehicleOwnerName()
                    , issueTicketRequestDto.getGateId());

        } catch (Exception ex){
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setFailureMessage(ex.getMessage());
            return  response;
        }

        response.setResponseStatus(ResponseStatus.SUCCESS);
        response.setTicket(ticket);

        return response;
    }
}
