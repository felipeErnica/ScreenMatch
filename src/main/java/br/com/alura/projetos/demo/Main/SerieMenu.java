package br.com.alura.projetos.demo.Main;

import br.com.alura.projetos.demo.models.Season;
import br.com.alura.projetos.demo.models.Serie;
import br.com.alura.projetos.demo.repository.SerieRepository;
import java.util.Optional;
import java.util.Scanner;

public class SerieMenu implements IMenu {

    private Main mainMenu;
    private Serie serie;
    private SerieRepository serieRepository;

    public SerieMenu(Serie serie, Main mainMenu,SerieRepository serieRepository) {
        this.mainMenu = mainMenu;
        this.serie = serie;
        this.serieRepository = serieRepository;
    }

    public void showMenu(){
        serie.printSerie();
        showOptions();
    }

    public void showOptions(){
        System.out.println("\nSelecione as opções:");
        System.out.println("1 - Apagar Série");
        System.out.println("2 - Ver Temporada");
        System.out.println("3 - Pesquisar Episódios");
        System.out.println("4 - Retornar");

        String response = new Scanner(System.in).nextLine();

        switch (response) {
            case "1" -> deleteSerie();
            case "2" ->showSeason();
            case "3" -> {
                EpisodesMenu episodesMenu = new EpisodesMenu(this,serie.getEpisodeList());
                episodesMenu.showMenu();
            }
            case "4" -> mainMenu.showMenu();
            default -> {
                System.out.println("Selecione uma opção válida!");
                showMenu();
            }
        }
    }

    private void deleteSerie() {
        serieRepository.deleteById(serie.getId());
        System.out.println("Série deletada!");
        mainMenu.showMenu();
    }

    private void showSeason() {
        System.out.println("Digite o Número da Temporada:");
        try {
            int seasonNumber = new Scanner(System.in).nextInt();
            Optional<Season> optionalSeason = serie.getSeasons().stream()
                    .filter(s -> s.getSeasonNumber() == seasonNumber)
                    .findFirst();
            if (optionalSeason.isPresent()) {
                Season season = optionalSeason.get();
                SeasonMenu seasonMenu = new SeasonMenu(season,this,serieRepository);
                seasonMenu.showMenu();
            } else {
                System.out.println("A Temporada não existe!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
