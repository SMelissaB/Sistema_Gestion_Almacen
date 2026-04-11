/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocio;

import CapaDatos.ProductoDAL;
import CapaRecursos.Producto;
import java.util.List;

/**
 *
 * @author melis
 */
public class ProductoBL {
    
    ProductoDAL oProductoDAL = new ProductoDAL();

    public List listarProductos() {
        return oProductoDAL.listar();
    }


    public Producto buscarPorNombre(String nombre) {
        return oProductoDAL.buscarPorNombre(nombre);
    }
    
        public Producto buscarPorCodigo(String codigo) {
        return oProductoDAL.buscarPorCodigo(codigo);
    }
}
