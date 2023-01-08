package com.accounting.HardwareAccounting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.accounting.HardwareAccounting.user.User", "com.accounting.HardwareAccounting.hardware.Hardware"})
public class HardwareAccountingApplication {


	public static void main(String[] args) {
		SpringApplication.run(HardwareAccountingApplication.class, args);
	}

}
