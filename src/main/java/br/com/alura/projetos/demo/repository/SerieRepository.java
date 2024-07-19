package br.com.alura.projetos.demo.repository;

import br.com.alura.projetos.demo.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository <Serie,Long> {
    Optional<Serie> findByTitleContainingIgnoreCase(String title);
    List<Serie> findByTotalSeasonsAndRatingsGreaterThan(int totalSeasons, double ratings);
}
