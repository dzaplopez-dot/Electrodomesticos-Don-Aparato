package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.conexion.ConexionBD;
import modelo.dto.CompraDTO;

public class CompraDAO {

    public int registrarCompra(CompraDTO dto) {
        int idGenerado = -1;
        ConexionBD conexion = new ConexionBD();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO compra "
                   + "(documento_cliente, total_sin_descuento, "
                   + "descuento, total_con_descuento, mensaje_descuento) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = conexion.getConnection();
            ps = conn.prepareStatement(sql,
                PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getDocumentoCliente());
            ps.setDouble(2, dto.getTotalSinDescuento());
            ps.setDouble(3, dto.getDescuentoPorcentaje());
            ps.setDouble(4, dto.getTotalConDescuento());
            ps.setString(5, dto.getMensajeDescuento());
            ps.execute();
            rs = ps.getGeneratedKeys();
            if (rs.next()) idGenerado = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Error al registrar compra: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) { e.printStackTrace(); }
            conexion.desconectar();
        }
        return idGenerado;
    }

    
    public ArrayList<CompraDTO> consultarPorCliente(String documento) {
        ArrayList<CompraDTO> lista = new ArrayList<>();
        ConexionBD conexion = new ConexionBD();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM compra WHERE documento_cliente = ?";
        try {
            Connection conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, documento);
            rs = ps.executeQuery();
            while (rs.next()) {
                CompraDTO dto = new CompraDTO();
                dto.setIdCompra           (rs.getInt   ("id"));
                dto.setDocumentoCliente   (rs.getString("documento_cliente"));
                dto.setTotalSinDescuento  (rs.getDouble("total_sin_descuento"));
                dto.setDescuentoPorcentaje(rs.getDouble("descuento"));
                dto.setTotalConDescuento  (rs.getDouble("total_con_descuento"));
                dto.setMensajeDescuento   (rs.getString("mensaje_descuento"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar compras: " + e.getMessage());
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
