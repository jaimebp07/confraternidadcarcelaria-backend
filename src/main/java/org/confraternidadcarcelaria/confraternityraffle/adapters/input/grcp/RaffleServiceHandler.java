package org.confraternidadcarcelaria.confraternityraffle.adapters.input.grcp;

import io.grpc.stub.StreamObserver;
import org.confraternidadcarcelaria.confraternityraffle.application.RaffleService;
import org.confraternidadcarcelaria.confraternityraffle.domain.RaffleTicket;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RaffleServiceHandler extends RaffleGrpcService.RaffleImplBase {

    @Inject
    RaffleService raffleService;

    @Override
    public void registerTicket(RegisterTicketRequest request, StreamObserver<RegisterTicketResponse> responseObserver) {
        String name = request.getNameContestant();
        String lastname = request.getLastnameContestant();
        String phone = request.getPhoneContestant();
        String state = request.getStateTicket();

        RaffleTicket raffleTicket = raffleService.registerTicket(name, lastname, phone, state);

        RegisterTicketResponse response = RegisterTicketResponse.newBuilder()
                .setTicketId(raffleTicket.getId())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
