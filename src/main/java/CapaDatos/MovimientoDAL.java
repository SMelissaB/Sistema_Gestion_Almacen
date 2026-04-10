/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melis
 */
public class MovimientoDAL {
    Connection cn = new ConexionBD().abrirConexion();

    public int registrar(String fecha, String tipo, String documento, int idUsuario) {
        int r=0;
        try {
            CallableStatement cst = cn.prepareCall("{call sp_registrar_movimiento(?, ?, ?, ?)}");
            cst.setString(1, fecha);
            cst.setString(2, tipo);
            cst.setString(3, documento);
            cst.setInt(4, idUsuario);
            
            ResultSet rs = cst.executeQuery();
            if (rs.next()) {
                r = rs.getInt(1); 
            }
            rs.close();
            cst.close();
        } catch (Exception e) {
            System.out.println("Error en registrar: " + e.getMessage());
            r = 0;
        }
        return r;
    }

    public List listar() {
        List lista = new ArrayList(); 
        try {
            CallableStatement cst = cn.prepareCall("{call sp_listar_movimientos()}");
            ResultSet rs = cst.executeQuery();
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getInt("id_movimiento");
                fila[1] = rs.getDate("fecha");
                fila[2] = rs.getString("tipo_movimiento");
                fila[3] = rs.getString("numero_documento");
                fila[4] = rs.getString("nombre_usuario");
                lista.add(fila);
            }
            cst.close();
        } catch (Exception e) {
            System.out.println("Error listar Mov: " + e.getMessage());
        }
        return lista;
    }
    
    public int registrarConTransaccion(String fecha, String tipo, String documento, int idUsuario, Connection cnTransaccional) throws Exception {
        int r = 0;
        // Usamos la conexión que el BL nos envía
        CallableStatement cst = cnTransaccional.prepareCall("{call sp_registrar_movimiento(?, ?, ?, ?)}");
        cst.setString(1, fecha);
        cst.setString(2, tipo);
        cst.setString(3, documento);
        cst.setInt(4, idUsuario);
        
        ResultSet rs = cst.executeQuery();
        if (rs.next()) {
            r = rs.getInt(1); 
        }
        rs.close();
        cst.close();
        return r;
    }
}
