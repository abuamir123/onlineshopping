package com.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopOnlineApplication {

	public static void main(String[] args) {
//		new File(ManagerController.uploadDirectory).mkdir();
		SpringApplication.run(ShopOnlineApplication.class, args);
	}

}
