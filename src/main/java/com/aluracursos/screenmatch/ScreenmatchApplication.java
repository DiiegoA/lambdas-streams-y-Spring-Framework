package com.aluracursos.screenmatch;

import com.aluracursos.screenmatch.menu.MenuPrincipal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MenuPrincipal menu = new MenuPrincipal();
		menu.muestraElMenu();
		/*EjemploStreams streams = new EjemploStreams();
		streams.muestraEjemplo();*/
	}
}
