package br.com.alura.projetos.demo.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity @Table(name = "episodes")
public class Episode {

    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private LocalDate release;
    private Long episodeNumber;
    private Double rating;
    @ManyToOne @JoinColumn(name = "serie_id")
    private Serie serie;
    @ManyToOne @JoinColumn(name = "season_id")
    private Season season;

    public Episode() {}
    public Episode (EpisodeData episodeData) {
        this.title = episodeData.title();
        this.release = episodeData.release().equals("N/A") ? null : LocalDate.parse(episodeData.release(), DateTimeFormatter.ISO_LOCAL_DATE);
        this.episodeNumber = Long.parseLong(episodeData.episodeNumber());
        this.rating = episodeData.rating().equals("N/A") ? 0 : Double.parseDouble(episodeData.rating());
    }

    public Long getId() {
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
    public LocalDate getRelease() {
        return release;
    }
    public void setRelease(LocalDate release) {
        this.release = release;
    }
    public Long getEpisodeNumber() {
        return episodeNumber;
    }
    public void setEpisodeNumber(Long episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public void setSeason(Season season) {
        this.season = season;
    }
    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public boolean equals(Object obj) {
        Episode episode = (Episode) obj;
        return episode.getTitle().equals(title) & episode.getRelease().equals(release);
    }

    @Override
    public String toString() {
        return season.getSeasonNumber() + "." + episodeNumber + " - " + title;
    }

}
