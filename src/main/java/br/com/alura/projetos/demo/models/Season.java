package br.com.alura.projetos.demo.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Season(@JsonAlias("Season") Integer season,
                     @JsonAlias("Episodes") List<Episode> episodes) {
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append("Season " + season + "\n");
        episodes.forEach(e -> stringBuilder.append(e.toString() + "\n"));
        return stringBuilder.toString();
    }
}
