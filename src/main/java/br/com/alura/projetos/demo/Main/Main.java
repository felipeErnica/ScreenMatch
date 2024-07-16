package br.com.alura.projetos.demo.Main;

import br.com.alura.projetos.demo.models.*;
import br.com.alura.projetos.demo.repository.SerieRepository;
import br.com.alura.projetos.demo.tools.DataConverter;
import br.com.alura.projetos.demo.tools.OmdbAdress;
import br.com.alura.projetos.demo.tools.APIConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private OmdbAdress adressGetter = new OmdbAdress();
    private APIConsumer apiConsumer = new APIConsumer();
    private DataConverter dataConverter = new DataConverter();
    private SerieRepository serieRepository;
    private List<Serie> serieList = new ArrayList<Serie>();

    public Main(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void showMenu(){
        System.out.println("Selecione uma opção:\n");
        System.out.println("1 - Buscar uma série");
        System.out.println("2 - Listar séries salvas");
        System.out.println("3 - Deletar séries");
        System.out.println("4 - Encerrar\n");

        int option = new Scanner(System.in).nextInt();

        switch (option) {
            case 1 -> addSeries();
            case 2 -> listSeries();
            case 3 -> deleteSeries();
            case 4 -> {}
            default -> {
                System.out.println("Selecione uma opção válida!\n");
                showMenu();
            }
        }

    }

    private void addSeries(){
        System.out.println("Insira uma Série para busca:");
        String search = new Scanner(System.in).nextLine();
        String uri = adressGetter.getURI(search);
        System.out.println(uri);
        String json = apiConsumer.getJson(uri);
        SerieData serieData = dataConverter.getData(json, SerieData.class);
        Serie serie = new Serie(serieData);

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            search = serieData.title() + "&season=" + i;
            String adress = adressGetter.getURI(search);
            json = apiConsumer.getJson(adress);
            Season season = dataConverter.getData(json,Season.class);
            for (EpisodeData episodeData: season.episodeData()) {
                Episode episode = new Episode(episodeData);
                serie.addEpisode(episode);
            }
        }
        serieRepository.save(serie);
        showMenu();
    }
    private void listSeries(){
        serieList = serieRepository.findAll();
        serieList.forEach(System.out::println);
        showMenu();
    }

    private void deleteSeries() {
        System.out.println("Insira o Id:");
        long id = new Scanner(System.in).nextLong();
        serieRepository.deleteById(id);
        listSeries();
    }

}
