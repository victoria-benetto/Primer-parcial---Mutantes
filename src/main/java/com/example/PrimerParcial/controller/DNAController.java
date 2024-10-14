package com.example.PrimerParcial.controller;


import com.example.PrimerParcial.dto.DNARequest;
import com.example.PrimerParcial.entities.DNA;
import com.example.PrimerParcial.service.DNAService;
import com.example.PrimerParcial.service.DNAServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") //permitir el acceso a la api desde distintos clientes
@RequestMapping(path = "api/v1/dna") //la url que necesitamos para acceder a los recursos
public class DNAController extends BaseControllerImpl<DNA, DNAServiceImpl>{
    @Autowired
    DNAService dnaService;

    @PostMapping("/mutant/")
    public ResponseEntity<?> detect(@RequestBody DNARequest dnaRequest){
        try{
            //Convertir List<String> a un solo String concatenado
            String dnaString = String.join(",", dnaRequest.getDna());

            //Crear una entidad DNA y establecer el valor del String
            DNA dnaEntity = new DNA();
            dnaEntity.setDna(dnaString);

            boolean isMutant = dnaService.isMutant(dnaEntity);

            if (isMutant) {
                return ResponseEntity.status(HttpStatus.OK).body("{\"mutant\":\"Mutante encontrado.\"}");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"mutant\":\"Mutante no encontrado.\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
