package com.worldline.perfectnumber;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Perfect Number API"))
public class PerfectNumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfectNumberApplication.class, args);
	}

}
