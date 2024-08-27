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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ggaro
 */
public class Ventas {

    public void guardarVentas(JTextField txtCodigoLibroVenta, JTextField txtCedulaClienteVenta, JTextField txtCantidadCompra, JTextField txtBuscarVenta, JTable TableListVenta) {

        String codigo = txtCodigoLibroVenta.getText();
        String cedulaCliente = txtCedulaClienteVenta.getText();
        String cantidad = txtCantidadCompra.getText();
        if (codigo.isEmpty() || cantidad.isEmpty() || cedulaCliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
        }

        int codigoLibro = Integer.parseInt(codigo);
        int cantidadCompra = Integer.parseInt(cantidad);
        Connection conect;
        PreparedStatement psVenta;
        PreparedStatement psActualizarCantidad;
        PreparedStatement psActualizarLibrosComprados;

        try {
            conect = getConnection();
            conect.setAutoCommit(false); // Iniciar transacción

            // Verificar si el cliente existe
            String checkClienteSql = "SELECT COUNT(*) FROM cliente WHERE deleted = 0 AND cedula = ?";
            PreparedStatement checkClientePs = conect.prepareStatement(checkClienteSql);
            checkClientePs.setString(1, cedulaCliente);
            ResultSet rsCliente = checkClientePs.executeQuery();
            rsCliente.next();

            if (rsCliente.getInt(1) <= 0) {
                JOptionPane.showMessageDialog(null, "Cliente no existe.");
                return;
            }

            // Verificar si el libro existe
            String checkLibroSql = "SELECT COUNT(*) FROM libro WHERE deleted = 0 AND codigo = ?";
            PreparedStatement checkLibroPs = conect.prepareStatement(checkLibroSql);
            checkLibroPs.setInt(1, codigoLibro);
            ResultSet rsLibro = checkLibroPs.executeQuery();
            rsLibro.next();

            if (rsLibro.getInt(1) <= 0) {
                JOptionPane.showMessageDialog(null, "Libro no existe.");
                return;
            }

            // Insertar venta
            String sqlVenta = "INSERT INTO venta (cedula, codigo, fechaVenta) VALUES (?, ?, NOW())";
            psVenta = conect.prepareStatement(sqlVenta);
            psVenta.setString(1, cedulaCliente);
            psVenta.setInt(2, codigoLibro);
            psVenta.executeUpdate();

            // Actualizar cantidad de libro
            String sqlActualizarCantidad = "UPDATE libro SET cantidad = cantidad - ? WHERE codigo = ?";
            psActualizarCantidad = conect.prepareStatement(sqlActualizarCantidad);
            psActualizarCantidad.setInt(1, cantidadCompra);
            psActualizarCantidad.setInt(2, codigoLibro);
            psActualizarCantidad.executeUpdate();

            // Actualizar libros comprados por el cliente
            String sqlActualizarLibrosComprados = "UPDATE cliente SET librosComprados = librosComprados + ? WHERE cedula = ?";
            psActualizarLibrosComprados = conect.prepareStatement(sqlActualizarLibrosComprados);
            psActualizarLibrosComprados.setInt(1, cantidadCompra);
            psActualizarLibrosComprados.setString(2, cedulaCliente);
            psActualizarLibrosComprados.executeUpdate();

            conect.commit(); // Confirmar transacción
            JOptionPane.showMessageDialog(null, "Venta registrada.");
            limpiarVentas(txtCodigoLibroVenta, txtCedulaClienteVenta, txtCantidadCompra, txtBuscarVenta);
            limpiarTabla(TableListVenta);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void cargarTablaVentas(JTable TableListVenta) {

        DefaultTableModel modeloTabla = (DefaultTableModel) TableListVenta.getModel();
        modeloTabla.setRowCount(0);
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        // Ajuste del tamaño de las columnas
        int[] anchos = {10, 10, 10, 50, 10, 50};
        for (int i = 0; i < TableListVenta.getColumnCount(); i++) {
            TableListVenta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        String sql = "SELECT c.nombre, c.apellido, c.cedula, l.nombre AS libro, l.codigo, v.fechaVenta "
                + "FROM venta v "
                + "JOIN cliente c ON v.cedula = c.cedula "
                + "JOIN libro l ON v.codigo = l.codigo";

        try {
            Connection conect = getConnection();
            ps = conect.prepareStatement(sql);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    if (i == 5) {
                        Timestamp timestamp = rs.getTimestamp(i + 1);
                        String fechaFormateada = formatoFecha.format(timestamp);
                        fila[i] = fechaFormateada;
                    } else {
                        fila[i] = rs.getObject(i + 1);
                    }
                }
                modeloTabla.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void buscarVentas(JTextField txtBuscarVenta, JTable TableListVenta) {

        String cedulaBusqueda = txtBuscarVenta.getText();

        if (cedulaBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, introduzca la cédula del cliente.");
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) TableListVenta.getModel();
        modeloTabla.setRowCount(0);
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        // Ajuste de ancho de columnas
        int[] anchos = {10, 10, 10, 50, 10, 50};
        for (int i = 0; i < TableListVenta.getColumnCount(); i++) {
            TableListVenta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        String sql = "SELECT c.nombre, c.apellido, c.cedula, l.nombre AS libro, l.codigo, v.fechaVenta "
                + "FROM venta v "
                + "JOIN cliente c ON v.cedula = c.cedula "
                + "JOIN libro l ON v.codigo = l.codigo";

        if (!cedulaBusqueda.isEmpty()) {
            sql += " WHERE c.cedula LIKE ?";
        }

        try {
            Connection conect = getConnection();
            ps = conect.prepareStatement(sql);

            if (!cedulaBusqueda.isEmpty()) {
                String criterio = "%" + cedulaBusqueda + "%";
                ps.setString(1, criterio);
            }

            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    if (i == 5) {
                        Timestamp timestamp = rs.getTimestamp(i + 1);
                        String fechaFormateada = formatoFecha.format(timestamp);
                        fila[i] = fechaFormateada;
                    } else {
                        fila[i] = rs.getObject(i + 1);
                    }
                }
                modeloTabla.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void limpiarTabla(JTable TableListVenta) {
        DefaultTableModel model = (DefaultTableModel) TableListVenta.getModel();
        model.setRowCount(0);
    }

    public void limpiarVentas(JTextField txtCodigoLibroVenta, JTextField txtCedulaClienteVenta,
            JTextField txtCantidadCompra, JTextField txtBuscarVenta) {

        txtCodigoLibroVenta.setText("");
        txtCedulaClienteVenta.setText("");
        txtCantidadCompra.setText("");
        txtBuscarVenta.setText("");
    }
}
