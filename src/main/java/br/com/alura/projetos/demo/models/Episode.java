package br.com.alura.projetos.demo.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record Episode(@JsonAlias("Title") String title,
                      @JsonAlias("Released") String release,
                      @JsonAlias("Episode") Integer episodeNumber,
                      @JsonAlias("imdbRating") String rating) {

    @Override
    public String toString() {
        return episodeNumber + " - " + title;
    }

}