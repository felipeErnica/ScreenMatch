package br.com.alura.projetos.demo.repository;

import br.com.alura.projetos.demo.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository <Serie,Long> {
}
