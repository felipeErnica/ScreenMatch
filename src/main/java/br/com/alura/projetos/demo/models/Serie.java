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
    private List<Episode> episodes = new ArrayList<>();

    public Serie (){}

    public Serie (SerieData serieData) {
        this.title = serieData.title();
        this.year = serieData.year();
        this.totalSeasons = serieData.totalSeasons();
        this.ratings = serieData.ratings().equals("N/A") ? 0 : Double.parseDouble(serieData.ratings());
        this.genre = serieData.genre();
    }

    public void addEpisode(Episode episode){
        episode.setSerie(this);
        episodes.add(episode);
    }

    @Override
    public String toString() {
        return id + " - " + title + " (" + year + ") " + "Nota: " + ratings;
    }
}
