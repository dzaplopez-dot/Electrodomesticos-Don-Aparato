package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.conexion.ConexionBD;
import modelo.dto.DetalleCompraDTO;

public class DetalleCompraDAO {

    // ── CREATE — guarda una línea del carrito ─────────────────────────────────
    public String registrarDetalle(DetalleCompraDTO dto) {
        String resultado = "error";
        ConexionBD conexion = new ConexionBD();
        PreparedStatement ps = null;

        String sql = "INSERT INTO detalle_compra "
                   + "(id_compra, id_producto, cantidad, subtotal) "
                   + "VALUES (?, ?, ?, ?)";
        try {
            Connection conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt   (1, dto.getIdCompra());
            ps.setInt   (2, dto.getIdProducto());
            ps.setInt   (3, dto.getCantidad());
            ps.setDouble(4, dto.getSubtotal());
            ps.execute();
            resultado = "ok";
        } catch (SQLException e) {
            System.out.println("Error al registrar detalle: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            conexion.desconectar();
        }
        return resultado;
    }

    // ── READ por compra ───────────────────────────────────────────────────────
    public ArrayList<DetalleCompraDTO> consultarPorCompra(int idCompra) {
        ArrayList<DetalleCompraDTO> lista = new ArrayList<>();
        ConexionBD conexion = new ConexionBD();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT dc.*, pc.nombre "
                   + "FROM detalle_compra dc "
                   + "JOIN producto_catalogo pc ON dc.id_producto = pc.id "
                   + "WHERE dc.id_compra = ?";
        try {
            Connection conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCompra);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleCompraDTO dto = new DetalleCompraDTO();
                dto.setIdCompra   (rs.getInt   ("id_compra"));
                dto.setIdProducto (rs.getInt   ("id_producto"));
                dto.setNombreProducto(rs.getString("nombre"));
                dto.setCantidad   (rs.getInt   ("cantidad"));
                dto.setSubtotal   (rs.getDouble("subtotal"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar detalle: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) { e.printStackTrace(); }
            conexion.desconectar();
        }
        return lista;
    }
}
