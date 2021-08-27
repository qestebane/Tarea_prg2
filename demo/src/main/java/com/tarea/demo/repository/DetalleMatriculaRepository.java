package com.tarea.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;
import com.tarea.demo.model.*;

import java.util.*;
@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleMatricula, Integer>{
    
    @Query(value = "SELECT o FROM DetalleMatricula o WHERE o.matricula=?1")
    List<DetalleMatricula> findItemsByFactura(Matricula matricula);
}
