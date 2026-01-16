package com.gestionalumnos.gestionalumnos.controller;

import com.gestionalumnos.gestionalumnos.domain.Alumno;
import com.gestionalumnos.gestionalumnos.model.AlumnoRepository;
import jakarta.persistence.Entity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<String> postAlumnos(@RequestBody Alumno alumno){
        alumnoRepo.save(alumno);
        return ResponseEntity.ok("Cliente Agregado con exito");
    }

        @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlumno(@PathVariable long id){
        return alumnoRepo.findById(id)
                .map(alumno -> {
                    alumnoRepo.delete(alumno);
                    return ResponseEntity.ok("Eliminado con éxito");
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Alumno con id " + id + " no encontrado"));
    }


}
