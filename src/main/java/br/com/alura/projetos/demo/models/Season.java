package br.com.alura.projetos.demo.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "seasons")
public class Season {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int seasonNumber;
    @Transient
    private double rating;
    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;
    @OneToMany(mappedBy = "season",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Episode> episodeList = new ArrayList<Episode>();

    public Season(){}
    public Season(SeasonData seasonData) {
        this.seasonNumber = seasonData.seasonNumber();
    }

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getSeasonNumber() {
        return seasonNumber;
    }
    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }
    public Serie getSerie() {
        return serie;
    }
    public void setSerie(Serie serie) {
        this.serie = serie;
    }
    public List<Episode> getEpisodeList() {
        return episodeList;
    }
    public String getTitle() {
        return null;
    }

    public double getRating(){
        if (rating==0) {
             rating = episodeList.stream()
                    .filter(e->e.getRating() != 0)
                    .mapToDouble(e -> e.getRating())
                    .average()
                    .getAsDouble();
        }
        return rating;
    }

    public void addEpisode (Episode episode) {
        episode.setSeason(this);
        episode.setSerie(serie);
        episodeList.add(episode);
    }

    public void printSeason () {
        System.out.println("Temporada " + seasonNumber + " - Avaliação " +  String.format("%.2f",getRating()));
        episodeList.forEach(System.out::println);
    }

    @Override
    public boolean equals(Object o) {
        Season season = (Season) o;
        return season.getSeasonNumber() == seasonNumber;
    }

    @Override
    public String toString() {
        return "Temporada " + seasonNumber;
    }

}
