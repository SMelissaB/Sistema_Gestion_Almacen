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
public class MovimientoDetalleDAL {
    Connection cn = new ConexionBD().abrirConexion();

    public int agregarDetalle(int idMov, int idProd, int cantidad, double precio, int saldo) {
        int r;
        try {
            CallableStatement cst = cn.prepareCall("{call sp_agregar_detalle(?, ?, ?, ?, ?)}");
            cst.setInt(1, idMov);
            cst.setInt(2, idProd);
            cst.setInt(3, cantidad);
            cst.setDouble(4, precio);
            cst.setInt(5, saldo);

            int f = cst.executeUpdate();
            r = (f > 0) ? 1 : 0;
            cst.close();
        } catch (Exception e) {
            r = 0;
        }
        return r;
    }

    public List listarPorMovimiento(int idMov) {
        List lista = new ArrayList();
        try {
            CallableStatement cst = cn.prepareCall("{call sp_listar_detalle_por_movimiento(?)}");
            cst.setInt(1, idMov);
            ResultSet rs = cst.executeQuery();
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getInt("id_detalle");
                fila[1] = rs.getString("nombre_producto");
                fila[2] = rs.getInt("cantidad");
                fila[3] = rs.getDouble("precio_historico");
                fila[4] = rs.getInt("saldo_momento");
                lista.add(fila);
            }
            cst.close();
        } catch (Exception e) {
            System.out.println("Error listar Detalle: " + e.getMessage());
        }
        return lista;
    }
    
    public void agregarDetalleConTransaccion(int idMov, int idProd, int cantidad, double precio, int saldo, Connection cnTransaccional) throws Exception {
        CallableStatement cst = cnTransaccional.prepareCall("{call sp_agregar_detalle(?, ?, ?, ?, ?)}");
        cst.setInt(1, idMov);
        cst.setInt(2, idProd);
        cst.setInt(3, cantidad);
        cst.setDouble(4, precio);
        cst.setInt(5, saldo);
        cst.executeUpdate();
        cst.close();
    }
    
    
    public List consultarMovimientos(int idProd) {
        List lista = new ArrayList();
        try {
            CallableStatement cst = cn.prepareCall("{call sp_consulta_movimientos_por_producto(?)}");
            cst.setInt(1, idProd);
            ResultSet rs = cst.executeQuery();
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("Fecha");
                fila[1] = rs.getString("Documento");
                fila[2] = rs.getObject("Entrada");
                fila[3] = rs.getObject("Salida");
                fila[4] = rs.getInt("Saldo");
                lista.add(fila);
            }
            cst.close();
        } catch (Exception e) {
            System.out.println("Error Kardex: " + e.getMessage());
        }
        return lista;
    }
}
