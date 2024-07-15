package br.com.alura.projetos.demo.models;

import br.com.alura.projetos.demo.tools.DataConverter;
import jakarta.persistence.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Formatter;

@Entity
@Table(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private LocalDate release;
    private Long episodeNumber;
    private String rating;

    @ManyToOne
    private Serie serie;

    public Episode(EpisodeData episodeData) {
        this.title = episodeData.title();
        this.release = LocalDate.parse(episodeData.release(), DateTimeFormatter.ISO_LOCAL_DATE);
        this.episodeNumber = Long.parseLong(episodeData.episodeNumber());
        this.rating = episodeData.rating();
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

}
