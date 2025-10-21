package Crear_tabla_BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class crearTabla {

    // Constantes de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/ut3";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        String sql = """
            CREATE TABLE clientes (
                DNI CHAR(9) NOT NULL PRIMARY KEY,
                APELLIDOS VARCHAR(32) NOT NULL,
                CP CHAR(5) NULL
            )
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Tabla clientes creada correctamente.");

        } catch (SQLException ex) {
            muestraErrorSQL(ex);
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        }
    }

    public static void muestraErrorSQL(SQLException ex) {
        System.out.println("Mensaje de error: " + ex.getMessage());
        System.out.println("Estado SQL: " + ex.getSQLState());
        System.out.println("Código de error: " + ex.getErrorCode());
    }
}