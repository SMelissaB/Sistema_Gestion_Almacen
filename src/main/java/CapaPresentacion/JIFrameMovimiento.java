/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacion;

import CapaNegocio.MovimientoBL;
import CapaRecursos.Sesion;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author melis
 */
public class JIFrameMovimiento extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFrameMovimiento
     */
    
    Integer idProduto = 0;
    
    // Datos para pruebas
    /*String[] nombresProd = {"-- Seleccione un Producto --", "Leche 1L", "Arroz 5kg", "Detergente", "Shampoo", "Agua 600ml", "Pollo 1kg"};
    int[] stocksProd = {0,100, 50, 30, 45, 200, 25}; 
    double[] preciosProd = { 0.0, 3.50, 18.20, 12.00, 15.50, 2.00, 14.80};
    int[] idsProd = { 0, 1, 2, 3, 4, 5, 6};
    */
    public JIFrameMovimiento() {
        initComponents();
        configurarCabecera();
        this.setClosable(true);
        this.setIconifiable(true);
    }

    private void configurarCabecera(){ 
        java.time.LocalDate hoy = java.time.LocalDate.now();
        lblFecha.setText(hoy.toString());

        // 2. Usuario: Corregimos el "Usuario: Usuario: null"
        // Validamos si la sesión tiene datos; si no, ponemos un genérico
        String nombreUsuario = CapaRecursos.Sesion.getUsername();
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            lblUsuario.setText("Usuario: Invitado");
        } else {
            lblUsuario.setText("Usuario: " + nombreUsuario);
        }

        // 3. Combo Tipo de Movimiento: Limpiar y cargar
        cboTipoMovimiento.removeAllItems();
        cboTipoMovimiento.addItem("ENTRADA");
        cboTipoMovimiento.addItem("SALIDA");

        // 4. Configuración de campos de texto
        txtStock.setEditable(false);
        txtStock.setText("0");
        txtPrecio.setText("0.00");
        lblTotal.setText("0.00");
        txtNroDocumento.setText(""); 

        // Le damos valores a las columnas de las tablas
        configurarTabla();
        
        cargarProductosCombo();
    }
    
    private void configurarTabla() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evitamos que editen directamente en la celda por ahora
            }
        };

        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Subtotal");

        tblDetalleMovimiento.setModel(modelo);

        // Ajustar anchos de columnas (opcional)
        tblDetalleMovimiento.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblDetalleMovimiento.getColumnModel().getColumn(1).setPreferredWidth(250);
    }
    
    private void cargarProductosCombo() {
        //cargarProductosMock();
        cargarProductosBD();
    }
    /*private void cargarProductosMock(){
        cboProducto.removeAllItems();
        for (String nombre : nombresProd) {
            cboProducto.addItem(nombre);
        }
    }*/
    
    private void cargarProductosBD(){
        
        cboProducto.removeAllItems();
        cboProducto.addItem("-- Seleccione un Producto --");

        // Asumimos que tienes un ProductoBL y un objeto de modelo Producto
        CapaNegocio.ProductoBL oProductoBL = new CapaNegocio.ProductoBL();
        java.util.List<CapaRecursos.Producto> lista = oProductoBL.listarProductos();

        for (CapaRecursos.Producto p : lista) {
            System.out.println(p.getNombre());
            cboProducto.addItem(p.getNombre());
        }
    }
    
    private boolean validarCabecera() {
        if (txtNroDocumento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el número de documento (Factura/Boleta).");
            txtNroDocumento.requestFocus();
            return false;
        }
        return true;
    }
    
    private void calcularTotalGeneral() {
        double total = 0;
        for (int i = 0; i < tblDetalleMovimiento.getRowCount(); i++) {
            total += Double.parseDouble(tblDetalleMovimiento.getValueAt(i, 4).toString());
        }
        lblTotal.setText("TOTAL : S/ " + String.format("%.2f", total));
    }
    
    
    private void limpiarFormularioCompleto() {
        // Limpiamos la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblDetalleMovimiento.getModel();
        modelo.setRowCount(0);

        // Limpiamos campos
        txtNroDocumento.setText("");
        txtStock.setText("0");
        txtPrecio.setText("0.00");
        spCantidad.setValue(0);
        cboProducto.setSelectedIndex(0);
        lblTotal.setText("TOTAL : S/ 0.00");

        // Ponemos el foco al inicio
        txtNroDocumento.requestFocus();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblFecha = new javax.swing.JLabel();
        cboTipoMovimiento = new javax.swing.JComboBox<>();
        lblUsuario = new javax.swing.JLabel();
        txtNroDocumento = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cboProducto = new javax.swing.JComboBox<>();
        txtStock = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        spCantidad = new javax.swing.JSpinner();
        btnAgregar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleMovimiento = new javax.swing.JTable();
        btnQuitarItem = new javax.swing.JButton();
        btnLimpiarTodo = new javax.swing.JButton();
        btnGuardarMovimiento = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();

        setTitle("REGISTRAR MOVIMIENTO");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cabecera"));

        lblFecha.setText("jLabel2");

        cboTipoMovimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTipoMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoMovimientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(cboTipoMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboTipoMovimiento)
                        .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto"));

        cboProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboProducto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboProductoItemStateChanged(evt);
            }
        });

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel2.setText("Producto");

        jLabel3.setText("Stock Actual");

        jLabel4.setText("Precio");

        jLabel5.setText("Cantidad");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(spCantidad))))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        tblDetalleMovimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblDetalleMovimiento);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnQuitarItem.setText("QUITAR ITEM");
        btnQuitarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarItemActionPerformed(evt);
            }
        });

        btnLimpiarTodo.setText("LIMPIAR TODO");
        btnLimpiarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarTodoActionPerformed(evt);
            }
        });

        btnGuardarMovimiento.setText("GUARDAR MOVIMIENTO");
        btnGuardarMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarMovimientoActionPerformed(evt);
            }
        });

        lblTotal.setText("TOTAL : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnQuitarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarMovimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnQuitarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLimpiarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboTipoMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoMovimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTipoMovimientoActionPerformed

    private void cboProductoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboProductoItemStateChanged
        //cboProductoStateChangedMock(evt);
        cboProductoStateChangedBD(evt);
    }//GEN-LAST:event_cboProductoItemStateChanged

    private void cboProductoStateChangedBD(java.awt.event.ItemEvent evt){
        // Solo actuamos cuando se SELECCIONA un elemento (evita que se ejecute dos veces)
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {

            String nombreProd = cboProducto.getSelectedItem().toString();

            if (!nombreProd.equals("-- Seleccione un Producto --")) {
                // 1. Llamamos a la lógica de negocio para buscar el producto por nombre
                CapaNegocio.ProductoBL oProductoBL = new CapaNegocio.ProductoBL();
                CapaRecursos.Producto p = oProductoBL.buscarPorNombre(nombreProd);

                if (p != null) {
                    // 2. Llenamos los campos automáticamente
                    System.out.println(p.getIdProducto());
                    this.idProduto = p.getIdProducto();
                    txtStock.setText(String.valueOf(p.getStock()));
                    txtPrecio.setText(String.valueOf(p.getPrecio()));
                    spCantidad.setValue(1); // Ponemos 1 por defecto
                    spCantidad.requestFocus(); // Saltamos el cursor a cantidad para ahorrar tiempo
                    
                    
                    
                }
            } else {
                // Si selecciona el mensaje de ayuda, limpiamos los campos
                txtStock.setText("0");
                txtPrecio.setText("0.00");
            }
        }
    }
    
    /*private void cboProductoStateChangedMock(java.awt.event.ItemEvent evt){
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            int index = cboProducto.getSelectedIndex();

            if (index > 0) { // Si no es el mensaje de "Seleccione..."
                txtStock.setText(String.valueOf(stocksProd[index]));
                txtPrecio.setText(String.valueOf(preciosProd[index]));
                spCantidad.setValue(1);
                spCantidad.requestFocus();
            } else {
                txtStock.setText("0");
                txtPrecio.setText("0.00");
            }
        }
    }*/
    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        //agregarProdcutoMock();
        agregarProdcutoBD();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarItemActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tblDetalleMovimiento.getModel();
        int filaSeleccionada = tblDetalleMovimiento.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Opcional: Pedir confirmación
            int resp = JOptionPane.showConfirmDialog(this, 
                    "¿Está seguro de quitar este producto del detalle?", 
                    "Quitar Item", JOptionPane.YES_NO_OPTION);

            if (resp == JOptionPane.YES_OPTION) {
                modelo.removeRow(filaSeleccionada);
                // ¡Importante! Recalcular el total después de quitar
                calcularTotalGeneral();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila de la tabla para quitar.");
        }
    }//GEN-LAST:event_btnQuitarItemActionPerformed

    private void btnLimpiarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarTodoActionPerformed
        int resp = JOptionPane.showConfirmDialog(this, 
                "¿Desea limpiar todos los datos del movimiento actual?", 
                "Limpiar Todo", JOptionPane.YES_NO_OPTION);

        if (resp == JOptionPane.YES_OPTION) {
            // 1. Limpiar la tabla (Detalle)
            DefaultTableModel modelo = (DefaultTableModel) tblDetalleMovimiento.getModel();
            modelo.setRowCount(0); 

            // 2. Limpiar campos de la sección Producto
            cboProducto.setSelectedIndex(0);
            txtStock.setText("0");
            txtPrecio.setText("0.00");
            spCantidad.setValue(0);

            // 3. Limpiar campos de la Cabecera (excepto fecha y usuario)
            txtNroDocumento.setText("");
            cboTipoMovimiento.setSelectedIndex(0);

            // 4. Resetear el total
            lblTotal.setText("TOTAL : S/ 0.00");

            txtNroDocumento.requestFocus();
        }
    }//GEN-LAST:event_btnLimpiarTodoActionPerformed

    private void btnGuardarMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarMovimientoActionPerformed
        // 1. Validaciones previas
        if (txtNroDocumento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el número de documento.");
            return;
        }
        if (tblDetalleMovimiento.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "El detalle está vacío. Agregue productos.");
            return;
        }

        // 2. Proceso de Guardado
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea registrar este movimiento?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                // Instanciamos la Lógica de Negocio
                CapaNegocio.MovimientoBL oMovimientoBL = new CapaNegocio.MovimientoBL();

                // A. Capturamos los datos de la cabecera
                String fecha = lblFecha.getText();
                String tipo = cboTipoMovimiento.getSelectedItem().toString();
                String documento = txtNroDocumento.getText();
                int idUsuario = 1; // Aquí pondrás el id real cuando tengas el login

                // B. Preparamos la lista del Detalle recorriendo la JTable
                // Usamos List<Object[]> porque es lo que definimos en el nuevo BL
                java.util.List<Object[]> detalle = new java.util.ArrayList<>();

                for (int i = 0; i < tblDetalleMovimiento.getRowCount(); i++) {
                    Object[] fila = new Object[4];
                    fila[0] = Integer.parseInt(tblDetalleMovimiento.getValueAt(i, 0).toString()); // ID Producto
                    fila[1] = tblDetalleMovimiento.getValueAt(i, 1).toString(); // Nombre (opcional)
                    fila[2] = Integer.parseInt(tblDetalleMovimiento.getValueAt(i, 2).toString()); // Cantidad
                    fila[3] = Double.parseDouble(tblDetalleMovimiento.getValueAt(i, 3).toString()); // Precio

                    detalle.add(fila);
                }

                // C. Enviamos todo al método transaccional del BL
                boolean exito = oMovimientoBL.registrarMovimientoCompleto(fecha, tipo, documento, idUsuario, detalle);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Movimiento registrado con éxito y stock actualizado.");
                    limpiarFormularioCompleto(); 
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo registrar el movimiento. Verifique los datos.");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error técnico: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnGuardarMovimientoActionPerformed

     /*private void agregarProdcutoMock(){
         int index = cboProducto.getSelectedIndex();
        if (index == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto.");
            return;
        }

        int id = idsProd[index]; // Obtenemos el ID del arreglo
        String nombre = nombresProd[index];
        int cant = (int) spCantidad.getValue();
        double precio = Double.parseDouble(txtPrecio.getText());
        double subtotal = cant * precio;

        // Validación de stock (Solo si es SALIDA)
        if (cboTipoMovimiento.getSelectedItem().toString().equals("SALIDA")) {
            if (cant > stocksProd[index]) {
                JOptionPane.showMessageDialog(this, "Stock insuficiente.");
                return;
            }
        }

        DefaultTableModel modelo = (DefaultTableModel) tblDetalleMovimiento.getModel();
        modelo.addRow(new Object[]{id, nombre, cant, precio, subtotal});

        calcularTotalGeneral();
     }*/

    private void agregarProdcutoBD(){
        // 1. Validaciones básicas
        if (cboProducto.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto.");
            return;
        }

        int cantidad = (int) spCantidad.getValue();
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.");
            return;
        }

        // 2. Validación de Stock si es una SALIDA
        if (cboTipoMovimiento.getSelectedItem().toString().equals("SALIDA")) {
            int stockActual = Integer.parseInt(txtStock.getText());
            if (cantidad > stockActual) {
                JOptionPane.showMessageDialog(this, "No hay suficiente stock. Disponible: " + stockActual);
                return;
            }
        }

        // 3. Calcular subtotal y agregar a la tabla
        double precio = Double.parseDouble(txtPrecio.getText());
        double subtotal = precio * cantidad;
        String nombreProd = cboProducto.getSelectedItem().toString();

        // Obtenemos el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblDetalleMovimiento.getModel();

        // Agregamos la fila (puedes añadir el ID del producto si lo tienes a la mano)
        modelo.addRow(new Object[]{ idProduto, nombreProd, cantidad, precio, subtotal });

        // 4. Actualizar el total general de la ventana
        calcularTotalGeneral();

        // 5. Limpiar para el siguiente producto
        cboProducto.setSelectedIndex(0);
        txtStock.setText("0");
        txtPrecio.setText("0.00");
        spCantidad.setValue(0);
        idProduto = 0;
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnGuardarMovimiento;
    private javax.swing.JButton btnLimpiarTodo;
    private javax.swing.JButton btnQuitarItem;
    private javax.swing.JComboBox<String> cboProducto;
    private javax.swing.JComboBox<String> cboTipoMovimiento;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JSpinner spCantidad;
    private javax.swing.JTable tblDetalleMovimiento;
    private javax.swing.JTextField txtNroDocumento;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
