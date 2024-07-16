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
    @ManyToOne private Serie serie;
    @ManyToOne private Season season;

    public Episode() {
    }
    public Episode (EpisodeData episodeData) {
        this.title = episodeData.title();
        this.release = episodeData.release().equals("N/A") ? null : LocalDate.parse(episodeData.release(), DateTimeFormatter.ISO_LOCAL_DATE);
        this.episodeNumber = Long.parseLong(episodeData.episodeNumber());
        this.rating = episodeData.rating().equals("N/A") ? 0 : Double.parseDouble(episodeData.rating());
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
    public void setSerie(Serie serie) {
        this.serie = serie;
    }
    public void setSeason(Season season) {
        this.season = season;
    }
    @Override
    public String toString() {
        return season.getSeasonNumber() + "." + episodeNumber + " - " + title;
    }
}
