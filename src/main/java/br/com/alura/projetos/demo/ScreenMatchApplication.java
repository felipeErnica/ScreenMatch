package br.com.alura.projetos.demo;

import br.com.alura.projetos.demo.models.DataConverter;
import br.com.alura.projetos.demo.models.OmdbAdress;
import br.com.alura.projetos.demo.models.Serie;
import br.com.alura.projetos.demo.tools.APIConsumer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Insira uma Série:");
		String search = new Scanner(System.in).next();
		String uri = new OmdbAdress().getURI(search);
		String json = new APIConsumer().getJson(uri);
		Serie serie = new DataConverter().getData(json,Serie.class);
		System.out.println(serie);

	}
}
