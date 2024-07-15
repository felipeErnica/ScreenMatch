package br.com.alura.projetos.demo.Main;

import br.com.alura.projetos.demo.models.*;
import br.com.alura.projetos.demo.repository.SerieRepository;
import br.com.alura.projetos.demo.tools.DataConverter;
import br.com.alura.projetos.demo.tools.OmdbAdress;
import br.com.alura.projetos.demo.tools.APIConsumer;
import br.com.alura.projetos.demo.tools.SeasonStreamer;

import java.util.Scanner;

public class Main {

    private Scanner scanner = new Scanner(System.in);
    private OmdbAdress adressGetter = new OmdbAdress();
    private APIConsumer apiConsumer = new APIConsumer();
    private DataConverter dataConverter = new DataConverter();
    private SerieRepository serieRepository;

    public Main(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void showMenu(){
        System.out.println("Insira uma Série:");
        String search = scanner.nextLine();
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
    }
}
