package modelo.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBD {

    private final String nombreBD = "don_aparato";
    private final String usuario  = "root";
    private final String password = "";          
    private final String url      =
        "jdbc:mysql://localhost:3306/" + nombreBD
        + "?useUnicode=true&serverTimezone=UTC";

    private Connection conn = null;

    public ConexionBD() {
        conectar();
    }

    private void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion exitosa a: " + nombreBD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado. Agrega el .jar al Build Path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error de conexion. Verifica que MySQL este encendido.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void desconectar() {
        conn = null;
    }
}