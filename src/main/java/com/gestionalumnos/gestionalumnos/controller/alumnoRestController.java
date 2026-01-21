package com.gestionalumnos.gestionalumnos.controller;

import com.gestionalumnos.gestionalumnos.domain.Alumno;
import com.gestionalumnos.gestionalumnos.model.AlumnoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestionalumnos.gestionalumnos.service.AlumnoService;

import java.util.List;
@RestController
@RequestMapping("/alumnos")
public class alumnoRestController {

    private final AlumnoService alumnoService;

    public alumnoRestController( AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public List<Alumno> getAlumno(){
        return alumnoService.getAlumno();
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Alumno> GetBuscarPorId(@PathVariable long id){
        return alumnoService.GetBuscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<String> postAlumnos(@RequestBody Alumno alumno){
        return alumnoService.postAlumnos(alumno);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAlumno(@PathVariable long id){
        return alumnoService.deleteAlumno(id);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> patchAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
       return alumnoService.patchAlumno(alumno, id);
    }

}
