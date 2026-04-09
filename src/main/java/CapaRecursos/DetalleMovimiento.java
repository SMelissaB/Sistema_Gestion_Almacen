/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaRecursos;

/**
 *
 * @author melis
 */
public class DetalleMovimiento {
    private int id_detalle;
    private int id_movimiento;
    private int id_producto;
    private int cantidad;

    public DetalleMovimiento() {
    }

    public DetalleMovimiento(int id_movimiento, int id_producto, int cantidad) {
        this.id_movimiento = id_movimiento;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    public DetalleMovimiento(int id_detalle, int id_movimiento, int id_producto, int cantidad) {
        this.id_detalle = id_detalle;
        this.id_movimiento = id_movimiento;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(int id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
