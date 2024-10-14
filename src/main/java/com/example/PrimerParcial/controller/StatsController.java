package com.example.PrimerParcial.controller;

import com.example.PrimerParcial.entities.DNAStats;
import com.example.PrimerParcial.service.DNAStatsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/dna/stats")
public class StatsController extends BaseControllerImpl<DNAStats, DNAStatsServiceImpl>{

@Autowired
DNAStatsServiceImpl dnaStatsService;

    @GetMapping("/calcular/")
    public ResponseEntity<?> calcularStats(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(dnaStatsService.calcularStats()); //contiene el status de la respuesta
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); //mensaje de error en JSON
        }
    }
}

