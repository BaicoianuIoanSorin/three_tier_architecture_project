package com.database;


import com.database.grpc_services.MainServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class DatabaseSpringBootConfiguration
{
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseSpringBootConfiguration.class);

    @Bean
    public void mainService() throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new MainServiceImpl()).build();
        server.start();
        server.awaitTermination();
    }
//    @Bean
//    UserRepository userRepository() throws MongoDBStartException {
//        LOG.info("Initializing user repository...");
//        return new UserRepositoryImpl();
//    }
//
//    @Bean
//    UserResourceImpl userResourceImpl() {
//        return new UserResourceImpl();
//    }
//
//    @Bean
//    Gson gson() {
//        return new Gson();
//    }
}
