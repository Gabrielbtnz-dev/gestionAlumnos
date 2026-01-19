package com.gestionalumnos.gestionalumnos.service;

import com.gestionalumnos.gestionalumnos.domain.Alumno;
import com.gestionalumnos.gestionalumnos.model.AlumnoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
}
