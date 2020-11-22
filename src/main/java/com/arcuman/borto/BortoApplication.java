package com.arcuman.borto;

import com.arcuman.borto.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class BortoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BortoApplication.class, args);
	}

}
