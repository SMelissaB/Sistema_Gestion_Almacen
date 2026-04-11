/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaRecursos;

/**
 *
 * @author BIENVENIDO
 */
public class ComboItem {
    private int id;
    private String nombre;

    public ComboItem(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }

    @Override
    public String toString() { return nombre; } // Esto es lo que se verá en el combo
}
