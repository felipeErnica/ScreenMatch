package br.com.alura.projetos.demo.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieData(@JsonAlias("Title") String title,
                        @JsonAlias("Year") String year,
                        @JsonAlias("totalSeasons") int totalSeasons,
                        @JsonAlias("imdbRating") String ratings,
                        @JsonAlias("Genre") String genre) {
}
