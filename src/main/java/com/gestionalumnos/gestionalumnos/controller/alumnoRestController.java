package com.gestionalumnos.gestionalumnos.controller;

import com.gestionalumnos.gestionalumnos.domain.Alumno;
import com.gestionalumnos.gestionalumnos.model.AlumnoRepository;
import jakarta.persistence.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/alumnos")
public class alumnoRestController {

    /*Repositorio de la tabla Alumnos*/
    private final AlumnoRepository alumnoRepo;

    public alumnoRestController(AlumnoRepository alumnoRepo) {
        this.alumnoRepo = alumnoRepo;
    }

    @GetMapping
    public List<Alumno> getAlumno(){
        return alumnoRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> GetBuscarPorId(@PathVariable long id){
        return alumnoRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
