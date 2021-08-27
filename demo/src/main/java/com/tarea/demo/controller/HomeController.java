package com.tarea.demo.controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.*;


@RestController
@RequestMapping(value ="api/tarea", produces ="application/json")
public class HomeController {

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> create(@RequestBody Alumno p){
        alumnoData.save(p);
        alumnoData.flush();
        return new ResponseEntity<Integer>(p.getCodigoAlumno(),HttpStatus.CREATED);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<int> saludo(@RequestParam int id){
        return  new ResponseEntity<int>(
            "Codigo Alumno " + id, HttpStatus.OK);
    }


}
