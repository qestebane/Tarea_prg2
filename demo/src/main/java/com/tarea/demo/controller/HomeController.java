package com.tarea.demo.controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.*;


@RestController
@RequestMapping(value ="api/tarea", produces ="application/json")
public class HomeController {

   
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> saludo(@RequestParam int id){
        return  new ResponseEntity<Integer>(
            "Codigo Alumno " + id, HttpStatus.OK);
    }


}
