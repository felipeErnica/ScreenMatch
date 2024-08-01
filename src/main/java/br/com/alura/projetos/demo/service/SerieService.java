package br.com.alura.projetos.demo.service;

import br.com.alura.projetos.demo.dto.SerieDTO;
import br.com.alura.projetos.demo.models.Serie;
import br.com.alura.projetos.demo.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDTO> getAll(){
        return returnListDTO(serieRepository.findAll());
    }

    public List<SerieDTO> getTop5(){
        return returnListDTO(serieRepository.getBest5Series());
    }

    public List<SerieDTO> getNew(){
        return returnListDTO(serieRepository.getNewSeries());
    }

    private static List<SerieDTO> returnListDTO ( List<Serie> series) {
        return  series.stream()
                .map(s -> serieDTO(s))
                .collect(Collectors.toList());
    }

    private static SerieDTO serieDTO (Serie serie){
        return new SerieDTO(
                serie.getId(),
                serie.getTitle(),
                serie.getYear(),
                serie.getTotalSeasons(),
                serie.getRatings(),
                serie.getGenre(),
                serie.getDescription()
        );
    }



}
