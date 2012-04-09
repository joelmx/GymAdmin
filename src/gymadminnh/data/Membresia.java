/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gymadminnh.data;

import java.util.Date;

/**
 *
 * @author joel
 */
public class Membresia {
    
    private int idMembresia;
    private Date fechaInicio;
    private short duracion;
    private double precio = 0;
    private String descripcion = "";

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getDuracion() {
        return duracion;
    }

    public void setDuracion(short duracion) {
        this.duracion = duracion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(int idMembresia) {
        this.idMembresia = idMembresia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
