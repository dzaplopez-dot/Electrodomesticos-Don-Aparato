package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.conexion.ConexionBD;
import modelo.dto.ClienteDTO;

public class ClienteDAO {

   
    public String registrarCliente(ClienteDTO dto) {
        String resultado = "error";
        ConexionBD conexion = new ConexionBD();
        PreparedStatement ps = null;

        String sql = "INSERT INTO cliente "
                   + "(documento, nombre, apellido, edad, telefono, tipo) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getDocumento());
            ps.setString(2, dto.getNombre());
            ps.setString(3, dto.getApellido());
            ps.setInt   (4, dto.getEdad());
            ps.setString(5, dto.getTelefono());
            ps.setString(6, dto.getTipo());
            ps.execute();
            resultado = "si";
        } catch (SQLException e) {
            System.out.println("Error al registrar cliente: " + e.getMessage());
            resultado = "no";
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            conexion.desconectar();
        }
        return resultado;
    }


    public ClienteDTO consultarPorDocumento(String documento) {
        ClienteDTO dto = null;
        ConexionBD conexion = new ConexionBD();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM cliente WHERE documento = ?";
        try {
            Connection conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, documento);
            rs = ps.executeQuery();
            if (rs.next()) {
                dto = new ClienteDTO();
                dto.setDocumento(rs.getString("documento"));
                dto.setNombre   (rs.getString("nombre"));
                dto.setApellido (rs.getString("apellido"));
                dto.setEdad     (rs.getInt   ("edad"));
                dto.setTelefono (rs.getString("telefono"));
                dto.setTipo     (rs.getString("tipo"));
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar cliente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) { e.printStackTrace(); }
            conexion.desconectar();
        }
        return dto;
    }

    
    public ArrayList<ClienteDTO> consultarLista() {
        ArrayList<ClienteDTO> lista = new ArrayList<>();
        ConexionBD conexion = new ConexionBD();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM cliente";
        try {
            Connection conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClienteDTO dto = new ClienteDTO();
                dto.setDocumento(rs.getString("documento"));
                dto.setNombre   (rs.getString("nombre"));
                dto.setApellido (rs.getString("apellido"));
                dto.setEdad     (rs.getInt   ("edad"));
                dto.setTelefono (rs.getString("telefono"));
                dto.setTipo     (rs.getString("tipo"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar lista: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) { e.printStackTrace(); }
            conexion.desconectar();
        }
        return lista;
    }

    
    public String eliminar(String documento) {
        String resultado = "error";
        ConexionBD conexion = new ConexionBD();
        PreparedStatement ps = null;

        String sql = "DELETE FROM cliente WHERE documento = ?";
        try {
            Connection conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, documento);
            ps.execute();
            resultado = "ok";
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            conexion.desconectar();
        }
        return resultado;
    }
}

