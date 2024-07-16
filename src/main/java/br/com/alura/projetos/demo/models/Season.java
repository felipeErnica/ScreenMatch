package br.com.alura.projetos.demo.models;

import jakarta.persistence.*;
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
    private Serie serie;
    @OneToMany(mappedBy = "season",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Episode> episodeList = new ArrayList<Episode>();

    public Season(){}
    public Season(SeasonData seasonData) {
        this.seasonNumber = seasonData.seasonNumber();
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
    public List<Episode> getEpisodeList() {
        return episodeList;
    }
    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }
    public double getRating(){
        if (rating==0) {
            episodeList.forEach(e -> rating=+ e.getRating());
            rating = rating/episodeList.size();
        }
        return rating;
    }

    @Override
    public String toString() {
        return "Temporada " + seasonNumber;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public void addEpisode (Episode episode) {
        episode.setSerie(serie);
        episode.setSeason(this);
        episodeList.add(episode);
    }

}
