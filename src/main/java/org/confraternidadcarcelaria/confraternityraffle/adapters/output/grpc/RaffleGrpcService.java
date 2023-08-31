package org.confraternidadcarcelaria.confraternityraffle.adapters.output.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.confraternidadcarcelaria.confraternityraffle.ports.output.grpc.RaffleServiceBlockingStub;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RaffleGrpcService {

    private final RaffleServiceBlockingStub blockingStub;

    public RaffleGrpcService() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:6565")
                .usePlaintext()
                .build();

        blockingStub = RaffleServiceGrpc.newBlockingStub(channel);
    }

    public RaffleServiceBlockingStub getBlockingStub() {
        return blockingStub;
    }
}
