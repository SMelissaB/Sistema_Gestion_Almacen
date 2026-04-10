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
public class Sesion {
    private static int idUsuario;
    private static String username;

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int id) {
        idUsuario = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String user) {
        username = user;
    }
}
