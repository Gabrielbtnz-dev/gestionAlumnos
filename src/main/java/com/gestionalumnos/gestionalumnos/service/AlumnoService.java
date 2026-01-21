package com.gestionalumnos.gestionalumnos.service;

import com.gestionalumnos.gestionalumnos.domain.Alumno;
import com.gestionalumnos.gestionalumnos.model.AlumnoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoReposi;

    public AlumnoService(AlumnoRepository alumnoReposi) {
        this.alumnoReposi = alumnoReposi;
    }

    public List<Alumno> getAlumno(){
        return alumnoReposi.findAll();
    }

    public ResponseEntity<Alumno> GetBuscarPorId(Long id){
        return alumnoReposi.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> postAlumnos( Alumno alumno){
        if (alumno.getId() > 0){
            return ResponseEntity.badRequest().body("Para agregar nuevos alumnos no envies el id");
        }
        alumnoReposi.save(alumno);
        return ResponseEntity.ok("alumno Agregado con exito");
    }

    public ResponseEntity<String> deleteAlumno( long id){
        return alumnoReposi.findById(id)
                .map(alumno -> {
                    alumnoReposi.delete(alumno);
                    return ResponseEntity.ok("Eliminado con éxito");
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Alumno con id " + id + " no encontrado"));
    }

    public ResponseEntity<String> patchAlumno(Alumno alumno, Long id) {
        return alumnoReposi.findById(id)
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
                    alumnoReposi.save(alumnoExistente);
                    return ResponseEntity.ok("Modificado con exito");
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Alumno con id " + id + " no encontrado"));

    }
}
