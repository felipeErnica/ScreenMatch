package br.com.alura.projetos.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.projetos.demo.models.Serie;

public interface SerieRepository extends JpaRepository <Serie,Long> {

    Optional<Serie> findByTitleIgnoreCase(String search);

    @Query("SELECT s FROM Serie s WHERE s.title ILike :search ORDER BY s.ratings DESC")
    List<Serie> findByTitleAndOrderByRating(String search);

    @Query("SELECT s FROM Serie s ORDER BY s.ratings DESC")
    List<Serie> orderByRating();

    @Query("SELECT s FROM Serie s ORDER BY s.ratings DESC LIMIT 5")
    List<Serie> getBest5Series();

    @Query("SELECT s FROM Serie s WHERE s.totalSeasons >= :totalSeasons AND s.ratings >= :ratings ORDER BY s.ratings DESC")
    List<Serie> getSeriesBySeasonsAndRating(int totalSeasons, double ratings);

    @Query("SELECT s FROM Serie s ORDER BY s.year DESC")
    List<Serie> getNewSeries ();

}
