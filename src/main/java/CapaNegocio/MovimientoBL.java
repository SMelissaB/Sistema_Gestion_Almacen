/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocio;

import CapaDatos.ConexionBD;
import CapaDatos.MovimientoDAL;
import CapaDatos.MovimientoDetalleDAL;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author melis
 */
public class MovimientoBL {
    MovimientoDAL oMovimientoDAL = new MovimientoDAL();
    MovimientoDetalleDAL oDetalleDAL = new MovimientoDetalleDAL();

    public List listarMovimientos() {
        return oMovimientoDAL.listar();
    }

    public int registrarMovimiento(String fecha, String tipo, String documento, int idUsuario) {
        return oMovimientoDAL.registrar(fecha, tipo, documento, idUsuario);
    }
    
    public boolean registrarMovimientoCompleto(String fecha, String tipo, String documento, int idUsuario, List<Object[]> detalle) {
        Connection cn = null;
        try {
            // El BL pide la conexión solo para iniciar la transacción
            cn = new ConexionBD().abrirConexion();
            cn.setAutoCommit(false); // <--- INICIO DE TRANSACCIÓN

            // 1. Insertamos Cabecera
            int idMov = oMovimientoDAL.registrarConTransaccion(fecha, tipo, documento, idUsuario, cn);

            if (idMov > 0) {
                // 2. Insertamos cada fila del detalle
                for (Object[] fila : detalle) {
                    // fila[0]=idProd, fila[2]=cantidad, fila[3]=precio
                    oDetalleDAL.agregarDetalleConTransaccion(idMov, (int)fila[0], (int)fila[2], (double)fila[3], 0, cn);
                }
                
                cn.commit(); // Si todo salió bien, MySQL guarda y el Trigger actualiza stock
                return true;
            } else {
                cn.rollback();
                return false;
            }
        } catch (Exception e) {
            try { if (cn != null) cn.rollback(); } catch (Exception ex) {}
            System.out.println("Error BL: " + e.getMessage());
            return false;
        } finally {
            try { if (cn != null) cn.close(); } catch (Exception ex) {}
        }
    }
}
