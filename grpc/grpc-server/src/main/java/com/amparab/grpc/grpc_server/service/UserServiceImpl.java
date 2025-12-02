package com.amparab.grpc.grpc_server.service;


import com.amparab.grpc_server.GetUserRequest;
import com.amparab.grpc_server.GetUserResponse;
import com.amparab.grpc_server.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUser(GetUserRequest request,
                        StreamObserver<GetUserResponse> responseObserver) {

        // Mimic database call
        String name = "Amruta";

        GetUserResponse response = GetUserResponse.newBuilder()
                .setId(request.getId())
                .setName(name)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
