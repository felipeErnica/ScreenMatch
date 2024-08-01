package br.com.alura.projetos.demo.dto;

public record SerieDTO (
    long id,
    String title,
    String year,
    int totalSeasons,
    double ratings,
    String genre,
    String description
) {}

