package br.com.alura.projetos.demo.tools;

import br.com.alura.projetos.demo.models.SeasonList;

import java.util.Comparator;
import java.util.List;

public class SeasonStreamer implements IStreamable {

    private SeasonList seasons;

    public SeasonStreamer(SeasonList seasons) {
        this.seasons = seasons;
    }

    public String getBest(int position) {

        List<EpisodeAnalizer> analizerList = seasons.stream()
            .flatMap(season -> season.episodeData().stream()
                    .map(e -> new EpisodeAnalizer(e.title(),e.release(),e.episodeNumber(),season.season(),e.rating()))
            )
            .toList();

        StringBuilder stringBuilder = new StringBuilder();
        analizerList.stream()
                .sorted(Comparator.comparing(EpisodeAnalizer::getRating).reversed())
                .limit(position)
                .forEach(e -> stringBuilder.append(e.toString()).append("\n"));

        return stringBuilder.toString();
    }


}
