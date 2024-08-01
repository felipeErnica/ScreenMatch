package br.com.alura.projetos.demo.controller;

import br.com.alura.projetos.demo.dto.SerieDTO;
import br.com.alura.projetos.demo.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    SerieService serieService;

    @GetMapping
    public List<SerieDTO> getSeries(){
        return serieService.getAll();
    }

    @GetMapping("/top5")
    public List<SerieDTO> getTop5Series(){
        return serieService.getTop5();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> getNewSeries(){
        return serieService.getNew();
    }

}
