package br.com.alura.projetos.demo.tools;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EpisodeAnalizer {

    private String title;
    private LocalDate release;
    private String episodeNumber;
    private double rating;
    private int seasonNumber;

    public double getRating() {
        return rating;
    }

    public EpisodeAnalizer(String title, String release, String episodeNumber, int seasonNumber , String rating) {
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.seasonNumber = seasonNumber;

        try {
            this.release = LocalDate.parse(release, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            this.release = null;
        }

        try {
            this.rating = Double.parseDouble(rating);
        } catch (Exception e) {
            this.rating = 0;
        }

    }

    @Override
    public String toString() {
        return seasonNumber + "-" +  episodeNumber + " - " + title + "(Avaliação: " + rating + ")";
    }
}
