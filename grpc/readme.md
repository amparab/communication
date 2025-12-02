# gRPC Demo

This project demonstrates a simple gRPC setup in Java using Spring Boot, with a server providing a UserService and a client calling it using a blocking stub.

## What is gRPC?
- gRPC is a RPC (Remote Procedure Call) framework by Google.
- It allows one application (client) to call methods on another application (server) as if it were a local object.
- It uses Protocol Buffers (.proto) for defining messages and service contracts.
- gRPC supports blocking (synchronous) and non-blocking (asynchronous/streaming) calls.

In this project, we are using a blocking stub (simplest request-response scenario).

## Project Structure

### Server (grpc-server)

- Runs a Spring Boot gRPC server on port `9090`.
- Implements the `UserService` defined in `user.proto`.
- Generates Java classes from `user.proto` using `protobuf-maven-plugin`.
- Example method: `getUser` — returns a user by ID.

### Client (grpc-client)

- Runs a Spring Boot application that uses Spring gRPC Client Starter.
- Injects the blocking stub automatically via `@GrpcClient`.
- Calls the server’s `getUser` method like a local method.

## How to Run
Before running the project, be sure to run `mvn clean install` which would generate the java classes.
After running the `grpc-client`, you would see the response recieved from the `grpc-server` logged as below.
```agsl
id: 1
name: "Amruta"
```

## How It Works Behind the Scenes

- The client calls `UserServiceBlockingStub.getUser()`.
- The stub sends a gRPC request over HTTP/2 to the server.
- The server receives the request, executes the `UserServiceImpl.getUser()` method.
- The server sends the response back as a gRPC message.
- The client stub returns the response as a normal Java object.

The client knows the server address via Spring Boot configuration: `grpc.client.user-service.address=static://localhost:9090`

The @GrpcClient("user-service") annotation tells Spring which server to connect to.

Spring Boot automatically creates a channel and blocking stub for that server.

`grpc.client.user-service.negotiation-type=plaintext` Tells the gRPC client how to communicate with the server:

- plaintext → no TLS / unencrypted HTTP/2
- tls → encrypted communication using TLS/SSL

By default, gRPC expects secure connections (TLS).