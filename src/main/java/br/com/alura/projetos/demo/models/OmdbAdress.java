package br.com.alura.projetos.demo.models;

public class OmdbAdress {

    private final String BODY = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=e2e23c80";

    public String getURI (String search) {
        search = search.replace(" ","+");
        return BODY + search + API_KEY;
    }
}
