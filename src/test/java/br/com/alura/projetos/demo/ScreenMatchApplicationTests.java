package br.com.alura.projetos.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScreenMatchApplicationTests implements CommandLineRunner {

	@Test
	void contextLoads() {
		SpringApplication.run(ScreenMatchApplicationTests.class,"");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("teste");
	}
}
