package br.com.alura.projetos.demo.models;

import br.com.alura.projetos.demo.tools.GoogleIA;
import jakarta.persistence.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "series")
public class Serie {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String year;
    private int totalSeasons;
    private double ratings;
    private String genre;
    private String description;
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Season> seasons = new ArrayList<Season>();
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private  List<Episode> episodeList = new ArrayList<Episode>();

    public Serie (){}

    public Serie (SerieData serieData) {
        try {
            this.title = serieData.title();
            this.year = serieData.year();
            this.totalSeasons = serieData.totalSeasons();
            this.ratings = serieData.ratings().equals("N/A") ? 0 : Double.parseDouble(serieData.ratings());
            this.genre = serieData.genre();
            this.description = GoogleIA.translatePrompt(serieData.plot());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}
    public int getTotalSeasons() {
        return totalSeasons;
    }
    public void setTotalSeasons(int totalSeasons) {
        this.totalSeasons = totalSeasons;
    }
    public double getRatings() {
        return ratings;
    }
    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public List<Season> getSeasons() {
        return seasons;
    }
    public List<Episode> getEpisodeList() {
        return episodeList;
    }


    public void addSeason(Season season){
        season.setSerie(this);
        seasons.add(season);
    }

    public void printSerie () {
        System.out.println(title);
        System.out.println("Anos de Atividade: " + year);
        System.out.println("Total de Temporadas: " + totalSeasons);
        System.out.println("Sinopse: " + description);
        seasons.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return id + " - " + title + " (" + year + ") " + "Nota: " + ratings;
    }

}
