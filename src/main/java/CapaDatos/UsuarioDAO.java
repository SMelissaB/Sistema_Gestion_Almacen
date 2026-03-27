/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

import java.sql.*;
import CapaRecursos.Usuario;

/**
 *
 * @author Usuario
 */
public class UsuarioDAO {

    ConexionBD conexion = new ConexionBD();

    public boolean validarLogin(String user, String pass) {
        String sql = "SELECT * FROM usuario WHERE username = ? AND password = ?";
        try (Connection con = conexion.abrirConexion(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, user);
            pst.setString(2, pass);

            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error en Login: " + e.getMessage());
            return false;
        } finally {
            conexion.cerrarConexion();
        }
    }
}
