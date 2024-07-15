package br.com.alura.projetos.demo.models;

import br.com.alura.projetos.demo.tools.APIConsumer;
import br.com.alura.projetos.demo.tools.DataConverter;
import br.com.alura.projetos.demo.tools.OmdbAdress;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SeasonList extends ArrayList<Season> {

    private APIConsumer apiConsumer = new APIConsumer();
    private OmdbAdress omdbAdress = new OmdbAdress();
    private DataConverter dataConverter = new DataConverter();

    public SeasonList(@NotNull SerieData serieData) {

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            String search = serieData.title() + "&season=" + i;
            String adress = omdbAdress.getURI(search);
            String json = apiConsumer.getJson(adress);
            Season season = dataConverter.getData(json,Season.class);
            this.add(season);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        this.forEach(s -> stringBuilder
            .append(s.toString())
            .append("\n")
        );

        return stringBuilder.toString();
    }
}
