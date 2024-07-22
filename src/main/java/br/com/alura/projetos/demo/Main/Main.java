package br.com.alura.projetos.demo.Main;

import br.com.alura.projetos.demo.models.*;
import br.com.alura.projetos.demo.repository.SerieRepository;
import br.com.alura.projetos.demo.tools.APIConsumer;
import br.com.alura.projetos.demo.tools.DataConverter;
import br.com.alura.projetos.demo.tools.OmdbAdress;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        System.out.println("1 - Buscar uma série online");
        System.out.println("2 - Listar séries salvas");
        System.out.println("3 - Pesquisar série");
        System.out.println("4 - Selecionar série");
        System.out.println("5 - Encerrar\n");

        String option = new Scanner(System.in).nextLine();

        switch (option) {
            case "1" -> addSeries();
            case "2" -> listSeries();
            case "3" -> searchSerie();
            case "4" -> selectSerie();
            case "5" -> System.out.println("\nAplicação encerrada!");
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
            SeasonData seasonData = dataConverter.getData(json, SeasonData.class);
            Season season = new Season(seasonData);
            serie.addSeason(season);
            for (EpisodeData episodeData : seasonData.episodeDataList()) {
                Episode episode = new Episode(episodeData);
                season.addEpisode(episode);
            }
        }

        Optional<Serie> optionalSerie = serieRepository.findByTitleIgnoreCase(serie.getTitle());
        optionalSerie.ifPresent(s -> editSeries(serie,s));

        serieRepository.save(serie);
        showMenu();
    }

    private void editSeries(Serie serie, Serie serieFound){
        serie.setId(serieFound.getId());
        for (Season season:serie.getSeasons()) {
            Optional<Season> optionalSeason = serieFound.getSeasons().stream()
                    .filter(s -> s.equals(season))
                    .findFirst();
            optionalSeason.ifPresent(s -> season.setId(s.getId()));
            for (Episode episode : season.getEpisodeList()) {
                Optional<Episode> optionalEpisode = serieFound.getEpisodeList().stream()
                        .filter(episode::equals)
                        .findFirst();
                optionalEpisode.ifPresent(e -> episode.setId(e.getId()));
            }
        }
    }

    private void listSeries(){
        serieList = serieRepository.findAll();
        serieList.forEach(System.out::println);
        showMenu();
    }

    private void selectSerie(){
        System.out.println("Digite o Id:");
        try {
            long id = new Scanner(System.in).nextLong();
            Optional<Serie> optionalSerie = serieRepository.findById(id);
            if (optionalSerie.isPresent()) {
                Serie serie = optionalSerie.get();
                SerieMenu serieMenu = new SerieMenu(serie,this,serieRepository);
                serieMenu.showMenu();
            } else {
                System.out.println("Digite um Id válido!");
                showMenu();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchSerie() {
        System.out.println("Selecione uma opção:");
        System.out.println("1- Pequisar por Nome");
        System.out.println("2- Ordenar por Avaliação");
        System.out.println("3- As Melhores Avaliadas");
        System.out.println("4- As Melhores para Maratonar");
        System.out.println("5- Retornar");

        String option = new Scanner(System.in).nextLine();
        switch (option) {
            case "1","2","3","4" -> returnList(option);
            case "5" -> showMenu();
            default -> {
                System.out.println("Selecione uma opção válida!");
                selectSerie();
            }
        }
    }

    private void returnList (String option){
        List<Serie> seriesFound = new ArrayList<Serie>();
        switch (option) {
            case "1" -> {
                System.out.println("Digite um trecho do título:");
                String search =new Scanner(System.in).nextLine();
                seriesFound = serieRepository.findByTitleContainingIgnoreCaseOrderByRatingsDesc(search);
            }
            case "2" -> seriesFound = serieRepository.findByOrderByRatingsDesc();
            case "3" -> seriesFound = serieRepository.findTop3ByOrderByRatings();
            case "4" -> {
                seriesFound = serieRepository
                    .findByTotalSeasonsLessThanEqualAndRatingsGreaterThanEqualOrderByRatingsDesc(4, 8);
            }
        }

        seriesFound.forEach(System.out::println);
        searchOptions();
    }

    private void searchOptions(){
        System.out.println("Selecione uma opção:");
        System.out.println("1- Selecionar Série");
        System.out.println("2- Retornar");

        String option = new Scanner(System.in).nextLine();

        switch (option) {
            case "1" -> selectSerie();
            case "2" -> searchSerie();
            default -> {
                System.out.println("Selecione uma opção válida!");
                searchOptions();
            }
        }
    }

}
