/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaRecursos;

import java.time.LocalDate;

/**
 *
 * @author melis
 */
public class Movimiento {
    private int idMovimiento;
    private LocalDate fecha;
    private String tipoMovimiento;
    private String nroDocumento;
    private int idUsuario;

    public Movimiento() {
    }
    
    public Movimiento(LocalDate fecha, String tipo_movimiento, String numero_documento) {
        this.fecha = fecha;
        this.tipoMovimiento = tipo_movimiento;
        this.nroDocumento = numero_documento;
    }
        
    public Movimiento(int id_movimiento, LocalDate fecha, String tipo_movimiento, String numero_documento) {
        this.idMovimiento = id_movimiento;
        this.fecha = fecha;
        this.tipoMovimiento = tipo_movimiento;
        this.nroDocumento = numero_documento;
    }

    public Movimiento(int idMovimiento, LocalDate fecha, String tipoMovimiento, String nroDocumento, int usuario) {
        this.idMovimiento = idMovimiento;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.nroDocumento = nroDocumento;
        this.idUsuario = usuario;
    }
    
    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    } 
}
