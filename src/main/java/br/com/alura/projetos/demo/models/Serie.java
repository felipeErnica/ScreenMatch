package br.com.alura.projetos.demo.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "series")
public class Serie {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String year;
    private int totalSeasons;
    private Double ratings;
    private String genre;
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Season> seasons = new ArrayList<Season>();
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episode> episodes = new ArrayList<Episode>();

    public Serie (){}

    public Serie (SerieData serieData) {
        this.title = serieData.title();
        this.year = serieData.year();
        this.totalSeasons = serieData.totalSeasons();
        this.ratings = serieData.ratings().equals("N/A") ? 0 : Double.parseDouble(serieData.ratings());
        this.genre = serieData.genre();
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
    public Double getRatings() {
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
    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }
    public List<Episode> getEpisodes() {
        return episodes;
    }
    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
    public void addSeason(Season season){
        season.setSerie(this);
        seasons.add(season);
    }
    @Override
    public String toString() {
        return id + " - " + title + " (" + year + ") " + "Nota: " + ratings;
    }
}
