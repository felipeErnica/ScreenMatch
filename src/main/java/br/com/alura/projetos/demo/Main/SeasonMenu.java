package br.com.alura.projetos.demo.Main;

import br.com.alura.projetos.demo.models.Episode;
import br.com.alura.projetos.demo.models.Season;
import br.com.alura.projetos.demo.repository.SerieRepository;
import org.apache.logging.log4j.util.PropertySource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SeasonMenu {

    private SerieMenu serieMenu;
    private Season season;
    private SerieRepository serieRepository;

    public SeasonMenu(Season season, SerieMenu serieMenu, SerieRepository serieRepository) {
        this.serieMenu = serieMenu;
        this.season = season;
        this.serieRepository = serieRepository;
    }

    public void showMenu(){
        season.printSeason();
        showOptions();
    }

    private void showOptions(){
        System.out.println("\nSelecione as opções:");
        System.out.println("1 - Pesquisar Episódio");
        System.out.println("2 - Retornar");

        String response = new Scanner(System.in).nextLine();

        switch (response) {
            case "1" ->searchOptions();
            case "2" -> serieMenu.showMenu();
            default -> {
                System.out.println("Selecione uma opção válida!");
                showOptions();
            }
        }
    }

    private void searchOptions() {
        System.out.println("1- Pesquisar por nome");
        System.out.println("2- Pesquisar por número");
        System.out.println("3- Ordenar por Avaliação");
        System.out.println("4 - Top 3");
        System.out.println("5 - Retornar");

        List<Episode> searchList = new ArrayList<Episode>();
        String option = new Scanner(System.in).nextLine();

        switch (option) {
            case "1" -> {
                System.out.println("Digite o título:");
                String name = new Scanner(System.in).nextLine();
                searchList = season.getEpisodeList().stream()
                        .filter(e -> e.getTitle().contains(name))
                        .toList();
                showList(searchList);
            }
            case "2" -> {
                System.out.println("Digite o número:");
                int number = new Scanner(System.in).nextInt();
                searchList= season.getEpisodeList().stream()
                        .filter(e -> e.getEpisodeNumber() == number)
                        .toList();
                showList(searchList);
            }
            case "3" -> {
                searchList = season.getEpisodeList().stream()
                        .sorted(Comparator.comparing(Episode::getRating).reversed())
                        .toList();
                showList(searchList);
            }
            case "4" -> {
                searchList = season.getEpisodeList().stream()
                        .sorted(Comparator.comparing(Episode::getRating).reversed())
                        .limit(3)
                        .toList();
                showList(searchList);
            }
            case "5" -> showOptions();
            default -> System.out.println("Selecione uma opção válida");
        }


    }

    private void showList(List searchList){
        if (searchList.isEmpty()) {
            System.out.println("Nada encontrado!");
            searchOptions();
        }
        else {
            searchList.forEach(System.out::println);
            showOptions();
        }
    }

}
