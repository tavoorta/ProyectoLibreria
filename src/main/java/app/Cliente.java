/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package app;

import static app.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author ggaro
 */
public class Cliente extends javax.swing.JFrame {

    /**
     * Creates new form Cliente
     */
    public Cliente() {
        initComponents();
        txtId.setVisible(false);
        cargarTabla();
        limiteInput(txtNombreCliente, 50);
        limiteInput(txtApellidoCliente, 50);
        limiteInput(txtCedulaCliente, 50);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTodosClientes = new javax.swing.JButton();
        btnBuscarCliente = new javax.swing.JButton();
        txtBuscarCliente = new javax.swing.JTextField();
        btnGuardarCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableListClient = new javax.swing.JTable();
        btnEliminarCliente = new javax.swing.JButton();
        btnClearTableCLiente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApellidoCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCedulaCliente = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        btnInicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Libreria Tres Gatos");

        btnTodosClientes.setText("Mostrar Clientes");
        btnTodosClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosClientesActionPerformed(evt);
            }
        });

        btnBuscarCliente.setText("Buscar por Cédula");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        btnGuardarCliente.setText("Agregar Cliente");
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });

        TableListClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idCliente", "Nombre", "Apellido", "Cedula", "Libros Comprados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableListClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableListClientMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableListClient);

        btnEliminarCliente.setText("Eliminar");
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnClearTableCLiente.setText("Limpiar Busqueda");
        btnClearTableCLiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearTableCLienteActionPerformed(evt);
            }
        });

        btnEditarCliente.setText("Editar");
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nombre del Cliente");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Apellido del Cliente");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cédula del Cliente");

        jLabel4.setText("CLIENTES");

        btnInicio.setText("INICIO");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCedulaCliente))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGuardarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTodosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearTableCLiente))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnInicio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(593, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClearTableCLiente)
                    .addComponent(btnTodosClientes)
                    .addComponent(btnEliminarCliente)
                    .addComponent(btnEditarCliente)
                    .addComponent(btnGuardarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4)
                    .addContainerGap(400, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTodosClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosClientesActionPerformed
        cargarTabla();
    }//GEN-LAST:event_btnTodosClientesActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        String cliente = txtBuscarCliente.getText();
        buscarLibro(cliente);
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed

        String nombre = txtNombreCliente.getText();
        String apellido = txtApellidoCliente.getText();
        String cedula = txtCedulaCliente.getText();

        if (!nombre.isEmpty() && !apellido.isEmpty() && !cedula.isEmpty()) {

            PreparedStatement ps;
            Connection conect = getConnection();
            try {
                String checkSql = "SELECT COUNT(*) FROM cliente WHERE cedula = ?";
                PreparedStatement checkPs = conect.prepareStatement(checkSql);
                checkPs.setString(1, cedula);
                ResultSet rs = checkPs.executeQuery();
                rs.next();

                if (rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "La cédula ya existe.");
                    return;
                }

                String sql
                        = "INSERT INTO "
                        + "cliente "
                        + "(nombre, apellido, cedula) "
                        + "VALUES "
                        + "(?,?,?)";
                ps = conect.prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, cedula);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
                cargarTabla();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
        }
    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed

        String cliente = txtId.getText();
        if (cliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, introduzca el cliente que desea eliminar.");
            return;
        }
        int id = Integer.parseInt(txtId.getText());

        String sql
                = "DELETE "
                + "FROM "
                + "cliente "
                + "WHERE "
                + "idCliente=?";
//        String sql
//                = "UPDATE "
//                + "cliente "
//                + "SET "
//                + "deleted = 0 "
//                + "WHERE "
//                + "idCliente=?";
        PreparedStatement ps;
        try {
            Connection conect = getConnection();
            ps = conect.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
            limpiar();
            cargarTabla();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnClearTableCLienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearTableCLienteActionPerformed
        DefaultTableModel model = (DefaultTableModel) TableListClient.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_btnClearTableCLienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed

        String cliente = txtId.getText();
        if (cliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, introduzca el cliente que desea editar.");
            return;
        }
        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombreCliente.getText();
        String apellido = txtApellidoCliente.getText();
        String cedula = txtCedulaCliente.getText();
        
        if (!nombre.isEmpty() && !apellido.isEmpty() && !cedula.isEmpty()) {

            try {
                PreparedStatement ps;
                Connection conect = getConnection();
                String sql
                        = "UPDATE "
                        + "cliente "
                        + "SET "
                        + "nombre=?, apellido=?, cedula=? "
                        + "WHERE "
                        + "idCliente = ?";
                ps = conect.prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, cedula);
                ps.setInt(4, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Registro Editado");
                limpiar();
                cargarTabla();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
        }

    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void TableListClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableListClientMouseClicked
        
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT "
                + "idCliente, nombre, apellido, cedula "
                + "FROM "
                + "cliente "
                + "WHERE "
                + "idCliente = ?";

        try {
            int fila = TableListClient.getSelectedRow();
            int id = Integer.parseInt(TableListClient.getValueAt(fila, 0).toString());

            Connection conect = getConnection();
            ps = conect.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                txtId.setText(String.valueOf(id));
                txtNombreCliente.setText(rs.getString("nombre"));
                txtApellidoCliente.setText(rs.getString("apellido"));
                txtCedulaCliente.setText(rs.getString("cedula"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_TableListClientMouseClicked

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void cargarTabla() {

        DefaultTableModel modeloTabla = (DefaultTableModel) TableListClient.getModel();
        modeloTabla.setRowCount(0);
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        //Ajuste tamaño tabla
        int[] anchos = {5, 50, 50, 20, 5};
        for (int i = 0; i < TableListClient.getColumnCount(); i++) {
            TableListClient.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        String sql = "SELECT "
                + "idCliente, nombre, apellido, cedula, librosComprados "
                + "FROM "
                + "cliente "
                + "ORDER BY cedula ASC ";
        try {
            Connection conect = getConnection();
            ps = conect.prepareStatement(sql);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();
            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modeloTabla.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    private void buscarLibro(String criterioBusqueda) {

        if (criterioBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, introduzca el nombre o la cédula del cliente.");
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) TableListClient.getModel();
        modeloTabla.setRowCount(0);
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        int[] anchos = {5, 10, 100, 10, 10};
        for (int i = 0; i < TableListClient.getColumnCount(); i++) {
            TableListClient.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        String sql = "SELECT "
                + "idCliente, nombre, apellido, cedula, librosComprados "
                + "FROM "
                + "cliente";

        if (criterioBusqueda != null && !criterioBusqueda.isEmpty()) {
            sql += " WHERE (cedula LIKE ? OR nombre LIKE ?)";
        }

        try {
            Connection conect = getConnection();
            ps = conect.prepareStatement(sql);

            if (criterioBusqueda != null && !criterioBusqueda.isEmpty()) {
                String criterio = "%" + criterioBusqueda + "%";
                ps.setString(1, criterio);
                ps.setString(2, criterio);
            }

            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modeloTabla.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    private void limiteInput(JTextField textField, int maxCharacters) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int remainingCharacters = maxCharacters - currentLength + length;

                if (remainingCharacters > 0) {
                    if (length <= remainingCharacters) {
                        super.replace(fb, offset, length, text, attrs);
                    } else {
                        String newStr = text.substring(0, remainingCharacters);
                        super.replace(fb, offset, length, newStr, attrs);
                    }
                }
            }
        });
    }

    private void limpiar() {
        txtId.setText("");
        txtNombreCliente.setText("");
        txtApellidoCliente.setText("");
        txtCedulaCliente.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }
// <editor-fold defaultstate="collapsed" desc="Variables declaration - do not modify">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableListClient;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnClearTableCLiente;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnTodosClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtApellidoCliente;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
// </editor-fold>
}
