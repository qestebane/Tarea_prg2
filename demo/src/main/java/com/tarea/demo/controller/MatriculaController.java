package com.tarea.demo.controller;

import com.tarea.demo.model.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.tarea.demo.repository.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="api/matricula", produces ="application/json")
public class FacturaController {

    private final MatriculaRepository facturaData;
    private final DetalleFacturaRepository detalleFacturaData;

    public FacturaController(FacturaRepository facturaData,
        DetalleFacturaRepository detalleFacturaData){
        this.facturaData = facturaData;
        this.detalleFacturaData = detalleFacturaData;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> create(@RequestBody Matricula p){
        facturaData.save(p);
        facturaData.flush(); //-> id
        Matricula generada = p;
        List<DetalleMatricula> listItems = p.getDetalleFacturas();
        listItems.stream().forEach(o -> o.setFactura(generada));
        detalleFacturaData.saveAllAndFlush(listItems);
        return new ResponseEntity<Integer>(p.getId(),HttpStatus.CREATED);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> create(@RequestBody Alumno p){
        alumnoData.save(p);
        alumnoData.flush();
        return new ResponseEntity<Integer>(p.getCodigoAlumno(),HttpStatus.CREATED);
    }

    @GetMapping(value = "/{numeroFactura}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Matricula> findByNumber(@PathVariable String numeroFactura){
        Optional<Matricula> optFactura =facturaData.findByNumero(numeroFactura);
        if(optFactura.isPresent()){
            Matricula factura = optFactura.get();
            List<DetalleMatricula> detalleFacturas = detalleFacturaData.findItemsByFactura(factura);
            factura.setDetalleFacturas(detalleFacturas);
            return new ResponseEntity<Matricula>(factura,HttpStatus.OK);
        }else{
            return new ResponseEntity<Matricula>(HttpStatus.NOT_FOUND);
        }

        
    }

    
}
