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
    private int idDetalle;
    private int idMovimiento;
    private int idProducto;
    private int cantidad;
    private double precioHistorico;
    
    public DetalleMovimiento() {
    }

    public DetalleMovimiento(int id_movimiento, int id_producto, int cantidad) {
        this.idMovimiento = id_movimiento;
        this.idProducto = id_producto;
        this.cantidad = cantidad;
    }

    public DetalleMovimiento(int id_detalle, int id_movimiento, int id_producto, int cantidad) {
        this.idDetalle = id_detalle;
        this.idMovimiento = id_movimiento;
        this.idProducto = id_producto;
        this.cantidad = cantidad;
    }

    public DetalleMovimiento(int idDetalle, int idMovimiento, int idProducto, int cantidad, double precioHistorico) {
        this.idDetalle = idDetalle;
        this.idMovimiento = idMovimiento;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioHistorico = precioHistorico;
    }
    
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioHistorico() {
        return precioHistorico;
    }

    public void setPrecioHistorico(double precioHistorico) {
        this.precioHistorico = precioHistorico;
    }

     
}
