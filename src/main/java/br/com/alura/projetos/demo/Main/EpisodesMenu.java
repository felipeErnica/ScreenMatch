package br.com.alura.projetos.demo.Main;

import br.com.alura.projetos.demo.models.Episode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EpisodesMenu {

    private IMenu menuOrigin;
    private List<Episode> episodeList;

    public EpisodesMenu(IMenu menuOrigin, List<Episode> episodeList) {
        this.menuOrigin = menuOrigin;
        this.episodeList = episodeList;
    }

    public void showMenu() {
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
                searchList = episodeList.stream()
                        .filter(e -> e.getTitle().toUpperCase().contains(name.toUpperCase()))
                        .toList();
                showList(searchList);
            }
            case "2" -> {
                System.out.println("Digite o número:");
                int number = new Scanner(System.in).nextInt();
                searchList= episodeList.stream()
                        .filter(e -> e.getEpisodeNumber() == number)
                        .toList();
                showList(searchList);
            }
            case "3" -> {
                searchList = episodeList.stream()
                        .sorted(Comparator.comparing(Episode::getRating).reversed())
                        .toList();
                showList(searchList);
            }
            case "4" -> {
                searchList = episodeList.stream()
                        .sorted(Comparator.comparing(Episode::getRating).reversed())
                        .limit(3)
                        .toList();
                showList(searchList);
            }
            case "5" -> menuOrigin.showOptions();
            default -> System.out.println("Selecione uma opção válida");
        }


    }

    private void showList(List searchList){
        if (searchList.isEmpty()) {
            System.out.println("Nada encontrado!");
            showMenu();
        }
        else {
            searchList.forEach(System.out::println);
            menuOrigin.showOptions();
        }
    }

}
