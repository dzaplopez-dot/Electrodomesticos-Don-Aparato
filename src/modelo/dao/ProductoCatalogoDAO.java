package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.conexion.ConexionBD;
import modelo.dto.ProductoCatalogoDTO;

public class ProductoCatalogoDAO {

    
    public ArrayList<ProductoCatalogoDTO> consultarTodos() {
        ArrayList<ProductoCatalogoDTO> lista = new ArrayList<>();
        ConexionBD conexion = new ConexionBD();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM producto_catalogo WHERE stock > 0";
        try {
            Connection conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductoCatalogoDTO dto = new ProductoCatalogoDTO();
                dto.setId            (rs.getInt   ("id"));
                dto.setNombre        (rs.getString ("nombre"));
                dto.setPrecioUnitario(rs.getDouble ("precio_unitario"));
                dto.setStock         (rs.getInt    ("stock"));
                dto.setCantidadSeleccionada(0);
                lista.add(dto);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar catalogo: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) { e.printStackTrace(); }
            conexion.desconectar();
        }
        return lista;
    }

    public String actualizarStock(int idProducto, int cantidadVendida) {
        String resultado = "error";
        ConexionBD conexion = new ConexionBD();
        PreparedStatement ps = null;

        String sql = "UPDATE producto_catalogo "
                   + "SET stock = stock - ? "
                   + "WHERE id = ? AND stock >= ?";
        try {
            Connection conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cantidadVendida);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidadVendida);
            int filas = ps.executeUpdate();
            resultado = filas > 0 ? "ok" : "sin_stock";
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            conexion.desconectar();
        }
        return resultado;
    }
}
