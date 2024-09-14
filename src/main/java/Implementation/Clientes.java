/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Implementation;

import static app.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ggaro
 */
public class Clientes {

    public void guardarCliente(JTextField txtId, JTextField txtNombreCliente, JTextField txtApellidoCliente, JTextField txtCedulaCliente, JTextField txtBuscarCliente, JTable TableListClient) {

        String nombre = txtNombreCliente.getText();
        String apellido = txtApellidoCliente.getText();
        String cedula = txtCedulaCliente.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || cedula.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
            return;
        }

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
            limpiarCliente(txtId, txtNombreCliente, txtApellidoCliente, txtCedulaCliente, txtBuscarCliente);
            cargarTablaCliente(TableListClient);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void cargarTablaCliente(JTable TableListClient) {

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
                + "WHERE "
                + "deleted = 0 "
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

    public void editarCliente(JTextField txtId, JTextField txtNombreCliente, JTextField txtApellidoCliente, 
            JTextField txtCedulaCliente, JTextField txtBuscarCliente, JTable TableListClient) {

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
                limpiarCliente(txtId, txtNombreCliente, txtApellidoCliente, txtCedulaCliente, txtBuscarCliente);
                cargarTablaCliente(TableListClient);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
        }
    }

    public void buscarCliente(JTextField txtBuscarCliente, JTable TableListClient) {
        
        String criterioBusqueda = txtBuscarCliente.getText();
        
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
                + "cliente "
                + "WHERE "
                + "deleted = 0 ";

        if (criterioBusqueda != null && !criterioBusqueda.isEmpty()) {
            sql += " AND (cedula LIKE ? OR nombre LIKE ?)";
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

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            } else {
                while (rs.next()) {
                    Object[] fila = new Object[columnas];
                    for (int i = 0; i < columnas; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    modeloTabla.addRow(fila);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
        
    public void eliminarCliente(JTextField txtId, JTextField txtNombreCliente, JTextField txtApellidoCliente, 
            JTextField txtCedulaCliente, JTextField txtBuscarCliente, JTable TableListClient) {

        String cliente = txtId.getText();
        if (cliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, introduzca el cliente que desea eliminar.");
            return;
        }
        int id = Integer.parseInt(txtId.getText());

        String sql
                = "UPDATE "
                + "cliente "
                + "SET "
                + "deleted = 1 "
                + "WHERE "
                + "idCliente=?";
        PreparedStatement ps;
        try {
            Connection conect = getConnection();
            ps = conect.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
            limpiarCliente(txtId, txtNombreCliente, txtApellidoCliente, txtCedulaCliente, txtBuscarCliente);
            cargarTablaCliente(TableListClient);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    
    
    public void clienteMouseClicked(JTextField txtId, JTextField txtNombreCliente, JTextField txtApellidoCliente, JTextField txtCedulaCliente, JTable TableListClient) {

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
    }
    
    public void limpiarTabla(JTable TableListClient) {
        DefaultTableModel model = (DefaultTableModel) TableListClient.getModel();
        model.setRowCount(0);
    }

    public void limpiarCliente(JTextField txtId, JTextField txtNombreCliente, JTextField txtApellidoCliente,
            JTextField txtCedulaCliente, JTextField txtBuscarCliente) {

        txtId.setText("");
        txtNombreCliente.setText("");
        txtApellidoCliente.setText("");
        txtCedulaCliente.setText("");
        txtBuscarCliente.setText("");
    }
}
