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

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Alumno> GetBuscarPorId(@PathVariable long id){
        return alumnoRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> postAlumnos(@RequestBody Alumno alumno){
        if (alumno.getId() > 0){
            return ResponseEntity.badRequest().body("Para agregar nuevos alumnos no envies el id");
        }
        alumnoRepo.save(alumno);
        return ResponseEntity.ok("Cliente Agregado con exito");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteAlumno(@PathVariable long id){
        return alumnoRepo.findById(id)
                .map(alumno -> {
                    alumnoRepo.delete(alumno);
                    return ResponseEntity.ok("Eliminado con éxito");
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Alumno con id " + id + " no encontrado"));
    }

    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<String> patchAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
       return alumnoRepo.findById(id)
                .map(alumnoExistente->{
            if (alumno.getNombre() != null){
                alumnoExistente.setNombre(alumno.getNombre());
            }
            if (alumno.getEdad() != null){
                alumnoExistente.setEdad(alumno.getEdad());
            }

            if(alumno.getCurso() != null){
                alumnoExistente.setCurso(alumno.getCurso());
            }

            if(alumno.getEmail() != null){
                alumnoExistente.setEmail(alumno.getEmail());
            }
            alumnoRepo.save(alumnoExistente);
            return ResponseEntity.ok("Modificado con exito");
        })
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Alumno con id " + id + " no encontrado"));

    }




}
