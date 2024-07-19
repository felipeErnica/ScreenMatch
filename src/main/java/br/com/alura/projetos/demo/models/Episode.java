package br.com.alura.projetos.demo.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity @Table(name = "episodes")
public class Episode {

    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private LocalDate release;
    private int episodeNumber;
    private double rating;
    private String imdbID;
    @ManyToOne @JoinColumn(name = "serie_id")
    private Serie serie;
    @ManyToOne @JoinColumn(name = "season_id")
    private Season season;

    public Episode() {}
    public Episode (EpisodeData episodeData) {
        this.title = episodeData.title();
        this.release = episodeData.release().equals("N/A") ? null : LocalDate.parse(episodeData.release(), DateTimeFormatter.ISO_LOCAL_DATE);
        this.episodeNumber = Integer.parseInt(episodeData.episodeNumber());
        this.rating = episodeData.rating().equals("N/A") ? 0 : Double.parseDouble(episodeData.rating());
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
    public LocalDate getRelease() {
        return release;
    }
    public void setRelease(LocalDate release) {
        this.release = release;
    }
    public int getEpisodeNumber() {
        return episodeNumber;
    }
    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
    public double getRating() {
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
    public String getImdbID() {
        return imdbID;
    }

    @Override
    public boolean equals(Object obj) {
        Episode episode = (Episode) obj;
        return episode.getImdbID().equals(imdbID);
    }

    @Override
    public String toString() {
        return season.getSeasonNumber() + "." + episodeNumber + " - " + title + " Avaliação: " + rating;
    }

}
