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
public class MedidasCliente {
    
    private int idMedida;
    private Date fecha;
    private double bicepDer;
    private double bicepIzq;
    private double pectoral;
    private double abdomen;
    private double cintura;
    private double peso;
    private double gluteos = 0;
    private double cadera = 0;
    private double piernaDer = 0;
    private double piernaIzq = 0;

    public double getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(double abdomen) {
        this.abdomen = abdomen;
    }

    public double getBicepDer() {
        return bicepDer;
    }

    public void setBicepDer(double bicepDer) {
        this.bicepDer = bicepDer;
    }

    public double getBicepIzq() {
        return bicepIzq;
    }

    public void setBicepIzq(double bicepIzq) {
        this.bicepIzq = bicepIzq;
    }

    public double getCadera() {
        return cadera;
    }

    public void setCadera(double cadera) {
        this.cadera = cadera;
    }

    public double getCintura() {
        return cintura;
    }

    public void setCintura(double cintura) {
        this.cintura = cintura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getGluteos() {
        return gluteos;
    }

    public void setGluteos(double gluteos) {
        this.gluteos = gluteos;
    }

    public int getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(int idMedida) {
        this.idMedida = idMedida;
    }

    public double getPectoral() {
        return pectoral;
    }

    public void setPectoral(double pectoral) {
        this.pectoral = pectoral;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPiernaDer() {
        return piernaDer;
    }

    public void setPiernaDer(double piernaDer) {
        this.piernaDer = piernaDer;
    }

    public double getPiernaIzq() {
        return piernaIzq;
    }

    public void setPiernaIzq(double piernaIzq) {
        this.piernaIzq = piernaIzq;
    }
    
}
