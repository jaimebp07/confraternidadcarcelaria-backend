package org.confraternidadcarcelaria.confraternityraffle.adapters.input.grcp;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class RaffleServiceGrpc {

    @Inject
    @ConfigProperty(name = "quarkus.grpc.port")
    int grpcPort;

    private Server server;

    public void start() throws IOException {
        server = ServerBuilder.forPort(grpcPort)
                .addService(new RaffleServiceHandler())
                .build()
                .start();
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
