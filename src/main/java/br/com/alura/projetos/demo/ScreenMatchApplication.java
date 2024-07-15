package br.com.alura.projetos.demo;

import br.com.alura.projetos.demo.Main.Main;
import br.com.alura.projetos.demo.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	@Autowired
	SerieRepository serieRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(serieRepository);
		main.showMenu();
	}
}
