package com.database.grpc_services;

import com.database.crud.CRUDDatabase;
import com.database.model.Main;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;

import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;


public class MainServiceImpl extends mainservice.MainServiceGrpc.MainServiceImplBase {
    private static final Logger LOG = getLogger(MainServiceImpl.class);
    @Override
    public void getMainById(mainservice.GetMainRequest request, StreamObserver<mainservice.GetMainResponse> responseObserver) {
        Optional<Main> main = Optional.empty();
        try {
           main = Optional.of(CRUDDatabase.INSTANCE.getMainById(request.getId()));
        }
        catch (Exception e){
            LOG.error("Error on getMainById with main ID: {}, exception: {}", request.getId(), e.getMessage());
        }

        // TODO wrap this method into private methods - readability
        mainservice.GetMainResponse response;
        response = main.map(value -> mainservice.GetMainResponse.newBuilder()
                .setMain(mainservice.Main.newBuilder()
                        .setId(value.getId())
                        .setName(value.getName())
                        .setArgs(value.getArgs())
                        .build())
                .setDatabaseResponse(mainservice.DatabaseResponse.OK)
                .build()).orElseGet(() -> mainservice.GetMainResponse.newBuilder()
                .setMain(mainservice.Main.newBuilder().build())
                .setDatabaseResponse(mainservice.DatabaseResponse.ERROR)
                .build());

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
