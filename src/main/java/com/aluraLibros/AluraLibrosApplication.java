package com.aluraLibros;

import com.aluraLibros.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AluraLibrosApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AluraLibrosApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		principal.mostrarMenu();

	}
}