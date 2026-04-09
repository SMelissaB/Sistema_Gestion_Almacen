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
    private int id_movimiento;
    private LocalDate fecha;
    private String tipo_movimiento;
    private String numero_documento;

    public Movimiento() {
    }
    
    public Movimiento(LocalDate fecha, String tipo_movimiento, String numero_documento) {
        this.fecha = fecha;
        this.tipo_movimiento = tipo_movimiento;
        this.numero_documento = numero_documento;
    }
        
    public Movimiento(int id_movimiento, LocalDate fecha, String tipo_movimiento, String numero_documento) {
        this.id_movimiento = id_movimiento;
        this.fecha = fecha;
        this.tipo_movimiento = tipo_movimiento;
        this.numero_documento = numero_documento;
    }

    public int getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(int id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }
    
    
}
