package com.tarea.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.tarea.demo.model.Alumno;

@RestController
@RequestMapping(value ="api/participante", produces ="application/json")
public class ParticipanteController {
    private Map<String, Alumno> participante;
    
    public ParticipanteController(){
        participante = new HashMap<String,Alumno>();
        Alumno p = new  Alumno();
        
        p.setNombre("nombre");
        p.setFechaNacimiento(new Date() )
        p.setGenero("genero");
        p.setCarrera("carrera");
        p.setBirthDate(new Date());
        String id = UUID.randomUUID().toString();
        p.setId(id);
        participante.put(id, p);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Alumno>> all(){
        return  new ResponseEntity<Map<String, Alumno>>(
            participante, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Alumno p){
        String id =UUID.randomUUID().toString();
        p.setId(id);
        participante.put(id, p);
        return  new ResponseEntity<String>(id,
             HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Alumno> find(@PathVariable String id){
        if(participante.containsKey(id)){
            Alumno p = participante.get(id);
            return new ResponseEntity<Alumno>(p, HttpStatus.OK);
        }else{
            return new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
        }
    }    

}
