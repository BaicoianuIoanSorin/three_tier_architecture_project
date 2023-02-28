package com.database;

import com.database.grpc_services.MainServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;

import java.io.IOException;

@DatabaseSpringBootApplication
public class DatabaseMainSpringBootApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(DatabaseMainSpringBootApplication.class);
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new MainServiceImpl()).build();
        server.start();
        server.awaitTermination();
    }
}