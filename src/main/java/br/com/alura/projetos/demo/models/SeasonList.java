package br.com.alura.projetos.demo.models;

import br.com.alura.projetos.demo.tools.APIConsumer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SeasonList extends ArrayList<Season> {

    public SeasonList(@NotNull Serie serie) {

        for (int i = 1; i <= serie.totalSeasons(); i++) {
            String search = serie.title() + "&season=" + i;
            String json = new APIConsumer().getJson(new OmdbAdress().getURI(search));
            Season season = new DataConverter().getData(json,Season.class);
            this.add(season);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.forEach(s -> stringBuilder.append(s.toString() + "\n"));
        return stringBuilder.toString();
    }
}
