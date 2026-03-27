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
public class Proveedor {
    private int idProveedor;
    private String codigo;
    private String nombre;

    public Proveedor(){
    }
    
    public Proveedor(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
        
    public Proveedor(int idProveedor, String codigo, String nombre) {
        this.idProveedor = idProveedor;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

 
}
