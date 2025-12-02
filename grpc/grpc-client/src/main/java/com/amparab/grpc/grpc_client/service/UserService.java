package com.amparab.grpc.grpc_client.service;

import com.amparab.grpc_server.GetUserRequest;
import com.amparab.grpc_server.GetUserResponse;
import com.amparab.grpc_server.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub blockingStub;

    public GetUserResponse getUser(int id) {
        return blockingStub.getUser(
                GetUserRequest.newBuilder()
                        .setId(id)
                        .build()
        );
    }
}
