package com.jackbourner.reactthymeleaf;

import com.jackbourner.reactthymeleaf.config.MyRuntimeHints;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(MyRuntimeHints.class)
public class ReactThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactThymeleafApplication.class, args);
	}
}