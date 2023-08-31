package org.confraternidadcarcelaria.confraternityraffle.adapters.input.rest;

import org.confraternidadcarcelaria.confraternityraffle.application.RaffleService;
import org.confraternidadcarcelaria.confraternityraffle.domain.RaffleTicket;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/raffle")
public class RestRaffleResource {

    @Inject
    RaffleService raffleService;

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerTicket(RaffleTicketRequest request) {
        String name = request.getNameContestant();
        String lastname = request.getLastnameContestant();
        String phone = request.getPhoneContestant();
        String state = request.getStateTicket();

        RaffleTicket raffleTicket = raffleService.registerTicket(name, lastname, phone, state);

        return Response.ok(new RaffleTicketResponse(raffleTicket.getId())).build();
    }
}
