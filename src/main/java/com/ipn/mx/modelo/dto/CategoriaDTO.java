/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Categoria;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author leoj_
 */
@Data
@AllArgsConstructor
public class CategoriaDTO implements Serializable{
    
    public Categoria entidad;

    public CategoriaDTO() {
        entidad = new Categoria();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Clave : ").append(getEntidad().getIdCategoria()).append("\n");
        sb.append("Nombre : ").append(getEntidad().getNombreCategoria()).append("\n");
        sb.append("Descripcion : ").append(getEntidad().getDescripcionCategoria()).append("\n");
        return sb.toString();
    }
    
    
    
}
