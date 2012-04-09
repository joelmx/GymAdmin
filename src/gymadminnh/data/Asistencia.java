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
public class Asistencia {
    
    private int idAsistencia;
    private Date fechaAsistencia;

    public Date getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(Date fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    
}
