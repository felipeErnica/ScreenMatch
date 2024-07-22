package br.com.alura.projetos.demo.Main;

import br.com.alura.projetos.demo.models.Season;
import br.com.alura.projetos.demo.repository.SerieRepository;
import java.util.Scanner;

public class SeasonMenu implements IMenu{

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

    public void showOptions(){
        System.out.println("\nSelecione as opções:");
        System.out.println("1 - Pesquisar Episódio");
        System.out.println("2 - Retornar");

        String response = new Scanner(System.in).nextLine();

        switch (response) {
            case "1" -> {
                EpisodesMenu episodesMenu = new EpisodesMenu(this,season.getEpisodeList());
                episodesMenu.showMenu();
            }
            case "2" -> serieMenu.showMenu();
            default -> {
                System.out.println("Selecione uma opção válida!");
                showOptions();
            }
        }
    }
}
