package com.gestionalumnos.gestionalumnos.model;

import com.gestionalumnos.gestionalumnos.domain.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
