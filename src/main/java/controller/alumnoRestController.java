package controller;

import domain.Alumno;
import model.AlumnoRepository;
import java.util.List;

public class alumnoRestController {

    /*Repositorio de la tabla Alumnos*/
    private final AlumnoRepository alumnoRepo;

    public alumnoRestController(AlumnoRepository alumnoRepo) {
        this.alumnoRepo = alumnoRepo;
    }



    public List<Alumno> getAlumno(){
        return alumnoRepo.findAll();
    }


}
