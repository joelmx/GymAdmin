/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gymadminnh.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author joel
 */
public class Cliente {
    
    private int idCliente;
    private String nombre;
    private Date nacidoCuando;
    private String direccion;
    private String email;
    private String telefono;
    private short edad;
    private ArrayList<MedidasCliente> listaMedidas = new ArrayList<MedidasCliente>();
    private ArrayList<Membresia> listaMembresias = new ArrayList<Membresia>();
    private ArrayList<Asistencia> listaAsistencias = new ArrayList<Asistencia>();
    
    public Cliente(){
    }
    
    public Cliente(String nombre){
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public short getEdad() {
        return edad;
    }

    public Date getNacidoCuando() {
        return nacidoCuando;
    }

    public void setNacidoCuando(Date nacidoCuando) {
        this.nacidoCuando = nacidoCuando;
        Calendar dob = Calendar.getInstance();  
        dob.setTime(nacidoCuando);  
        Calendar today = Calendar.getInstance();  
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))  
            age--;  
        this.edad = (short) age;   
    }

    public ArrayList<MedidasCliente> getListaMedidas() {
        return listaMedidas;
    }

    public void setListaMedidas(ArrayList<MedidasCliente> listaMedidas) {
        this.listaMedidas = listaMedidas;
    }

    public ArrayList<Membresia> getListaMembresias() {
        return listaMembresias;
    }

    public void setListaMembresias(ArrayList<Membresia> listaMembresias) {
        this.listaMembresias = listaMembresias;
    }

    public ArrayList<Asistencia> getListaAsistencias() {
        return listaAsistencias;
    }

    public void setListaAsistencias(ArrayList<Asistencia> listaAsistencias) {
        this.listaAsistencias = listaAsistencias;
    }
}