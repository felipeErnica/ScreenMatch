package br.com.alura.projetos.demo.models;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "seasons")
public class Season {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Integer seasonNumber;
    @Transient
    private Double rating;
    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;
    @OneToMany(mappedBy = "season",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Episode> episodeList = new ArrayList<Episode>();

    public Season(){}
    public Season(SeasonData seasonData) {
        this.seasonNumber = seasonData.seasonNumber();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getSeasonNumber() {
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

    public double getRating(){
        if (rating==0) {
            episodeList.forEach(e -> rating=+ e.getRating());
            rating = rating/episodeList.size();
        }
        return rating;
    }

    public void addEpisode (Episode episode) {
        episode.setSeason(this);
        episode.setSerie(serie);
        episodeList.add(episode);
    }

    @Override
    public boolean equals(Object o) {
        Season season = (Season) o;
        return season.getSeasonNumber().equals(seasonNumber);
    }

    @Override
    public String toString() {
        return "Temporada " + seasonNumber;
    }

}
