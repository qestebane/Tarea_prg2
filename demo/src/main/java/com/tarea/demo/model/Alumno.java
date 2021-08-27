package com.tarea.demo.model;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alumno {

    private String id;
    private String nombre;
    private Date FechaNacimiento;
    private String Genero;
    private String Carrera;
    
}
