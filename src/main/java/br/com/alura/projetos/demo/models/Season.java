package br.com.alura.projetos.demo.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Season(@JsonAlias("Season") Integer season,
                     @JsonAlias("Episodes") List<EpisodeData> episodeData) {
    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder()
                .append("Season ")
                .append(season)
                .append("\n");

        episodeData.forEach(e -> stringBuilder
                .append(e.toString())
                .append("\n")
        );

        return stringBuilder.toString();
    }
}
