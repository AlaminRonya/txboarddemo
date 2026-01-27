package com.sdlcpro.txdemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class TxdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TxdemoApplication.class, args);
	}

}
/**
 * http://localhost:8585/swagger-ui/index.html
 * http://localhost:8585/tx-board/ui/index.html
 */