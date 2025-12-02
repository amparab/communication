package com.amparab.grpc.grpc_client;

import com.amparab.grpc.grpc_client.service.UserService;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcClientApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(GrpcClientApplication.class, args);

		UserService userService = context.getBean(UserService.class);

		System.out.println(userService.getUser(1));
	}

}
