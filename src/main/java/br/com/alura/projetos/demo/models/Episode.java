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
    private String rating;
    @ManyToOne private Serie serie;

    public Episode() {
    }

    public Episode (EpisodeData episodeData) {
        this.title = episodeData.title();
        if (!episodeData.release().equals("N/A")) {
            this.release = LocalDate.parse(episodeData.release(), DateTimeFormatter.ISO_LOCAL_DATE);
        } else {
            this.release = null;
        }
        this.episodeNumber = Long.parseLong(episodeData.episodeNumber());
        this.rating = episodeData.rating();
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

}
