/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocio;

import CapaDatos.MovimientoDetalleDAL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melis
 */
public class MovimientoDetalleBL {
    MovimientoDetalleDAL oDetalleDAL = new MovimientoDetalleDAL();

    public List listarDetallePorMovimiento(int idMovimiento) {
        return oDetalleDAL.listarPorMovimiento(idMovimiento);
    }

    public int agregarDetalleMovimiento(int idMov, int idProd, int cantidad, double precio, int saldo) {
        if (cantidad <= 0) {
            return 0;
        }
        return oDetalleDAL.agregarDetalle(idMov, idProd, cantidad, precio, saldo);
    }
    
    public List consultarMovimientos(int idProd) {
        return oDetalleDAL.consultarMovimientos(idProd);
    }
}
