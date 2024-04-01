package br.com.alura.projetos.demo.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Serie(@JsonAlias("Title") String title,
                    @JsonAlias("Year") String year,
                    @JsonAlias("totalSeasons") int totalSeasons,
                    @JsonAlias("imdbRating") double ratings,
                    @JsonAlias("Genre") String genre) {
}
