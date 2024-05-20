package com.aluraLibros;

import com.aluraLibros.principal.Principal;
import com.aluraLibros.repository.RepositoryLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AluraLibrosApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AluraLibrosApplication.class, args);
	}



	@Autowired
	private RepositoryLibros repositorio;


	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(repositorio);
		principal.mostrarMenu();

	}
}