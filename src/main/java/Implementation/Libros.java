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
public class Libros {

    public void guardarLibro(JTextField txtId, JTextField txtCodigoLibro, JTextField txtNombreLibro, JTextField txtCantidadLibro, JTextField txtPrecioLibro, JTextField txtBuscarLibro, JTable tblLibros) {

        String codigoLibro = txtCodigoLibro.getText();
        String nombre = txtNombreLibro.getText();
        String cantidad = txtCantidadLibro.getText();
        String precio = txtPrecioLibro.getText().replace(",", ".");
        if (codigoLibro.isEmpty() || nombre.isEmpty() || cantidad.isEmpty() || precio.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
            return;
        }

        int cantidadLibro = Integer.parseInt(cantidad);
        double precioLibro;
        try {
            precioLibro = Double.parseDouble(precio);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un precio válido.");
            return;
        }
        PreparedStatement ps;
        Connection conect = getConnection();
        try {
            String checkSql = "SELECT COUNT(*) FROM libro WHERE codigo = ?";
            PreparedStatement checkPs = conect.prepareStatement(checkSql);
            checkPs.setString(1, codigoLibro);
            ResultSet rs = checkPs.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "El código de libro ya existe. Por favor, ingrese un código diferente.");
                return;
            }

            String sql
                    = "INSERT INTO "
                    + "libro "
                    + "(codigo, precio, cantidad, nombre) "
                    + "VALUES "
                    + "(?,?,?,?)";
            ps = conect.prepareStatement(sql);
            ps.setString(1, codigoLibro);
            ps.setDouble(2, precioLibro);
            ps.setInt(3, cantidadLibro);
            ps.setString(4, nombre);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Guardado");
            limpiarLibros(txtId, txtCodigoLibro, txtNombreLibro, txtCantidadLibro, txtPrecioLibro, txtBuscarLibro);
            cargarTablaLibros(tblLibros);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void cargarTablaLibros(JTable tblLibros) {

        DefaultTableModel modeloTabla = (DefaultTableModel) tblLibros.getModel();
        modeloTabla.setRowCount(0);
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        //Ajuste tamaño tabla
        int[] anchos = {5, 10, 100, 10, 10};
        for (int i = 0; i < tblLibros.getColumnCount(); i++) {
            tblLibros.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        String sql = "SELECT "
                + "idLibro, codigo, nombre, precio, cantidad "
                + "FROM "
                + "libro "
                + "WHERE "
                + "deleted = 0";
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

    public void editarLibro(JTextField txtId, JTextField txtCodigoLibro, JTextField txtNombreLibro, JTextField txtCantidadLibro, JTextField txtPrecioLibro, JTextField txtBuscarLibro, JTable tblLibros) {

        String editar = txtId.getText();
        if (editar.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, introduzca el libro que desea editar.");
            return;
        }
        int id = Integer.parseInt(txtId.getText());
        String codigoLibro = txtCodigoLibro.getText();
        String nombre = txtNombreLibro.getText();
        String cantidad = txtCantidadLibro.getText();
        String precio = txtPrecioLibro.getText().replace(",", ".");
        if (!codigoLibro.isEmpty() && !nombre.isEmpty() && !cantidad.isEmpty() && !precio.isEmpty()) {

            int cantidadLibro = Integer.parseInt(cantidad);
            double precioLibro;
            try {
                precioLibro = Double.parseDouble(precio);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un precio válido.");
                return;
            }
            try {
                PreparedStatement ps;
                Connection conect = getConnection();
                String sql
                        = "UPDATE "
                        + "libro "
                        + "SET "
                        + "codigo=?, precio=?, cantidad=?, nombre=? "
                        + "WHERE "
                        + "idLibro = ?";
                ps = conect.prepareStatement(sql);
                ps.setString(1, codigoLibro);
                ps.setDouble(2, precioLibro);
                ps.setInt(3, cantidadLibro);
                ps.setString(4, nombre);
                ps.setInt(5, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Registro Editado");
                limpiarLibros(txtId, txtCodigoLibro, txtNombreLibro, txtCantidadLibro, txtPrecioLibro, txtBuscarLibro);
                cargarTablaLibros(tblLibros);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
        }
    }

    public void eliminarLibro(JTextField txtId, JTextField txtCodigoLibro, JTextField txtNombreLibro, JTextField txtCantidadLibro, JTextField txtPrecioLibro, JTextField txtBuscarLibro, JTable tblLibros) {

        String delete = txtId.getText();
        if (delete.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, introduzca el libro que desea eliminar.");
            return;
        }
        int id = Integer.parseInt(txtId.getText());

        String sql
                = "UPDATE "
                + "libro "
                + "SET "
                + "deleted = 1 "
                + "WHERE "
                + "idLibro=?";
        PreparedStatement ps;
        try {
            Connection conect = getConnection();
            ps = conect.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
            limpiarLibros(txtId, txtCodigoLibro, txtNombreLibro, txtCantidadLibro, txtPrecioLibro, txtBuscarLibro);
            cargarTablaLibros(tblLibros);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void librosMouseClicked(JTextField txtId, JTextField txtCodigoLibro, JTextField txtNombreLibro, JTextField txtCantidadLibro, JTextField txtPrecioLibro, JTable tblLibros) {

        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT "
                + "idLibro, codigo, nombre, precio, cantidad "
                + "FROM "
                + "libro "
                + "WHERE "
                + "idLibro = ?";

        try {
            int fila = tblLibros.getSelectedRow();
            int id = Integer.parseInt(tblLibros.getValueAt(fila, 0).toString());

            Connection conect = getConnection();
            ps = conect.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                txtId.setText(String.valueOf(id));
                txtCodigoLibro.setText(rs.getString("codigo"));
                txtNombreLibro.setText(rs.getString("nombre"));
                txtPrecioLibro.setText(rs.getString("precio"));
                txtCantidadLibro.setText(rs.getString("cantidad"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void buscarLibro(JTextField txtBuscarLibro, JTable tblLibros) {
        
        String criterioBusqueda = txtBuscarLibro.getText();
        
        if (criterioBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, introduzca el nombre o el código del libro.");
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) tblLibros.getModel();
        modeloTabla.setRowCount(0);
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        int[] anchos = {5, 10, 100, 10, 10};
        for (int i = 0; i < tblLibros.getColumnCount(); i++) {
            tblLibros.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        String sql = "SELECT idLibro, codigo, nombre, precio, cantidad FROM libro WHERE deleted = 0";
        if (criterioBusqueda != null && !criterioBusqueda.isEmpty()) {
            sql += " AND (codigo LIKE ? OR nombre LIKE ?)";
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
                JOptionPane.showMessageDialog(null, "Libro no encontrado.");
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
    
    public void limpiarTabla(JTable tblLibros) {
        DefaultTableModel model = (DefaultTableModel) tblLibros.getModel();
        model.setRowCount(0);
    }

    public void limpiarLibros(JTextField txtId, JTextField txtCodigoLibro, JTextField txtNombreLibro,
            JTextField txtCantidadLibro, JTextField txtPrecioLibro, JTextField txtBuscarLibro) {

        txtId.setText("");
        txtCodigoLibro.setText("");
        txtNombreLibro.setText("");
        txtCantidadLibro.setText("");
        txtPrecioLibro.setText("");
        txtBuscarLibro.setText("");
    }
}
