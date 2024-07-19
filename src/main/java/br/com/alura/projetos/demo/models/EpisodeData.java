package br.com.alura.projetos.demo.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(@JsonAlias("Title") String title,
                          @JsonAlias("Released") String release,
                          @JsonAlias("Episode") String episodeNumber,
                          @JsonAlias("imdbRating") String rating,
                          @JsonAlias("imdbID") String imdbID) {

    @Override
    public String toString() {
        return episodeNumber + " - " + title;
    }

}