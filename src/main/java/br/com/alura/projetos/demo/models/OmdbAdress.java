package br.com.alura.projetos.demo.models;

public class OmdbAdress {

    private final String body = "http://www.omdbapi.com/?t=";
    private final String apiKey = "&apikey=e2e23c80";

    public String getURI (String search) {
        return body + search + apiKey;
    }
}
