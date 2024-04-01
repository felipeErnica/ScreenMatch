package br.com.alura.projetos.demo.Main;

import br.com.alura.projetos.demo.models.DataConverter;
import br.com.alura.projetos.demo.models.OmdbAdress;
import br.com.alura.projetos.demo.models.SeasonList;
import br.com.alura.projetos.demo.models.Serie;
import br.com.alura.projetos.demo.tools.APIConsumer;

import java.util.Scanner;

public class Main {

    private Scanner scanner = new Scanner(System.in);
    private OmdbAdress adressGetter = new OmdbAdress();
    private  APIConsumer apiConsumer = new APIConsumer();
    private DataConverter dataConverter = new DataConverter();

    public void showMenu(){
        System.out.println("Insira uma Série:");
        String search = scanner.nextLine();
        String uri = adressGetter.getURI(search);
        System.out.println(uri);
        String json = apiConsumer.getJson(uri);
        Serie serie = dataConverter.getData(json,Serie.class);
        System.out.println(serie);
        SeasonList seasonList = new SeasonList(serie);
        System.out.println(seasonList.toString());
    }
}
